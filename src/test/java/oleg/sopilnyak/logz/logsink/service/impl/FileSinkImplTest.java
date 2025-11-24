package oleg.sopilnyak.logz.logsink.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class FileSinkImplTest {
    private PutMessageServiceImpl delegate = new PutMessageServiceImpl();
    private FileSinkImpl service = new FileSinkImpl(50, delegate);

    @BeforeEach
    void setUp() {
        service.initMessagesQueueScan();
        delegate.logDirectory = ".";
        delegate.logFileName="filesink.log";
        delegate.makeLoggerPath();
    }
    @AfterEach
    void tearDown() {
        service.stopMessagesQueueScan();
    }

    @Test
    void shouldWriteOne() throws InterruptedException, IOException {
        service.write("message");

        Thread.sleep(100);

        assertNotEquals(Files.lines(delegate.loggerPath).filter("message"::equals).findFirst(), Optional.empty());
    }
}