package oleg.sopilnyak.logz.logsink.service.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import oleg.sopilnyak.logz.logsink.service.PutMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


/**
 * Service-Facade-Implementation: delivery message to file-system's log-file
 */
@Slf4j
@Service
public class PutMessageServiceImpl implements PutMessageService {
    @Value("${logz.logsink.directoryPath:.}")
    String logDirectory;
    @Value("${logz.logsink.logFile:application.log}")
    String logFileName;
    Path loggerPath;

    @PostConstruct
    void makeLoggerPath() {
        log.debug("Making path for directory '{}' and file '{}'", logDirectory, logFileName);
        loggerPath = new File(logDirectory, logFileName).toPath();
    }

    /**
     * To put message to the log-file
     *
     * @param logMessage message to put
     * @throws IOException throws if it cannot put the message to log-file
     */
    @Override
    public void putLogMessage(String logMessage) throws IOException {
        final String message = logMessage + System.lineSeparator();
        Files.writeString(loggerPath, message, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
    }
}
