package oleg.sopilnyak.logz.logsink.service;

/**
 * Service-Facade: Thread-safe service to put string-messages to the file
 */
public interface FileSink {
    /**
     * To write one line to configured file
     *
     * @param logMessage message to put
     */
    void write(String logMessage);
}