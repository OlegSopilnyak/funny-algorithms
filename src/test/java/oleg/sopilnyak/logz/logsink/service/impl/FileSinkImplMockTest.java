package oleg.sopilnyak.logz.logsink.service.impl;

import oleg.sopilnyak.logz.logsink.service.PutMessageService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class FileSinkImplMockTest {

    private PutMessageService delegate = mock(PutMessageService.class);
    private FileSinkImpl service = new FileSinkImpl(50, delegate);

    @BeforeEach
    void setUp() {
        service.initMessagesQueueScan();
    }

    @AfterEach
    void tearDown() {
        service.stopMessagesQueueScan();
    }

    @Test
    void testWriteOne() throws InterruptedException, IOException {
        service.write("message");

        Thread.sleep(100);

        verify(delegate).putLogMessage("message");
    }
}