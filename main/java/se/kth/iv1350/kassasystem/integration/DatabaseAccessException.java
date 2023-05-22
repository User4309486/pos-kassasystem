package main.java.se.kth.iv1350.kassasystem.integration;

/**
 * Will represent an exception that happens when there is an issue accessing the
 * database.
 */
public class DatabaseAccessException extends Exception {
    /**
     * Constructs a new object of DatabaseAccessException with the specified error
     * message and calls the superclass constructor with it.
     *
     * @param errorMessage The error message describing the cause of the exception
     *                     that is written to the log
     */
    public DatabaseAccessException(String errorMessage) {
        super(errorMessage);
    }
}