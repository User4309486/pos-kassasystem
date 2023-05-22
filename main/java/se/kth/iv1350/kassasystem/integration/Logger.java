package main.java.se.kth.iv1350.kassasystem.integration;

/**
 * Defines an interface for an object capable of logging messages and does not
 * have the location of the log.
 */
public interface Logger {

    /**
     * Logs a message.
     * 
     * @param message The message to be logged.
     */
    void log(String message);
}
