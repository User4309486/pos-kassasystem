package main.java.se.kth.iv1350.kassasystem.integration;

/**
 * Will print messages to <code>System.out</code>.
 */
public class ConsoleLogger implements Logger {

    /**
     * Will print one string to <code>System.out</code>.
     *
     * @param message The string that's about to be printed to
     *                <code>System.out</code>.
     */
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}
