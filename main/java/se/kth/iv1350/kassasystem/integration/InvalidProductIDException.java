package main.java.se.kth.iv1350.kassasystem.integration;

/**
 * Will represent an exception that happens when the searched product does not
 * exist.
 */
public class InvalidProductIDException extends Exception {
    /**
     * Constructs a new object of InvalidProductIDException with the specified error
     * message and calls the superclass constructor with it.
     *
     * @param errorMessage The error message describing the cause of the exception
     *                     that is written to the log
     */
    public InvalidProductIDException(String errorMessage) {
        super(errorMessage);
    }
}