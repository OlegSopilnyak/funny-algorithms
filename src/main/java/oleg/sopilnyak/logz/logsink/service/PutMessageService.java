package oleg.sopilnyak.logz.logsink.service;

import java.io.IOException;

/**
 * Service-Facade: delivery message to file-system's log-file
 */
public interface PutMessageService {
    /**
     * To put message to the log-file
     *
     * @param logMessage message to put
     * @throws IOException throws if cannot put the message to log-file
     */
    void putLogMessage(String logMessage) throws IOException;
}
