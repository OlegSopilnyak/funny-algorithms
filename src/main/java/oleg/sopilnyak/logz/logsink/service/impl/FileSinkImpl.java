package oleg.sopilnyak.logz.logsink.service.impl;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import oleg.sopilnyak.logz.logsink.service.FileSink;
import oleg.sopilnyak.logz.logsink.service.PutMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * Service-Facade-Implementation: Thread-safe service to put string-messages to the file
 */
@Slf4j
@Service
public class FileSinkImpl implements FileSink {
    private final long scanLogQueueDelay;

    public FileSinkImpl(
            @Value("${logz.logsink.scan.queue.delay:50}")long scanLogQueueDelay,
            PutMessageService deliveryService) {
        this.scanLogQueueDelay = scanLogQueueDelay;
        this.deliveryService = deliveryService;
    }

    // deliverer to log-file
    private final PutMessageService deliveryService;
    private final BlockingQueue<String> messages = new LinkedBlockingQueue<>();
    // threads-pool executor
    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @PostConstruct
    void initMessagesQueueScan() {
        log.info("Starting messages-queue scanning");
        executorService.schedule(this::deliveryMessage, scanLogQueueDelay, TimeUnit.MILLISECONDS);
    }

    @PreDestroy
    void stopMessagesQueueScan() {
        log.info("Stopping messages-queue scanning");
        executorService.shutdownNow();
    }

    /**
     * To write one line to configured file
     *
     * @param logMessage message to put
     */
    @Override
    public void write(String logMessage) {
        log.debug("Put to queue log-message '{}'", logMessage);
        messages.offer(logMessage);
    }

    private void deliveryMessage() {
        log.debug("Checking messages-queue...");
        final int capacity = 100;
        final List<String> messagesToDelivery = new ArrayList<>(capacity);
        while (!messages.isEmpty()) {
            int read = messages.drainTo(messagesToDelivery, capacity);
            log.debug("From queue we got {} messages", read);
            long deliver = messagesToDelivery.stream().limit(read)
                    .map(this::delivery)
                    .filter(Objects::nonNull).count();
            log.debug("Delivered {} message(s)", deliver);
        }
        log.debug("Scheduling next scan-iteration...");
        executorService.schedule(this::deliveryMessage, scanLogQueueDelay, TimeUnit.MILLISECONDS);
    }

    private String delivery(String message) {
        try {
            deliveryService.putLogMessage(message);
            return message;
        } catch (IOException e) {
            log.error("Cannot put message '{}'", message, e);
            return null;
        }
    }
}
