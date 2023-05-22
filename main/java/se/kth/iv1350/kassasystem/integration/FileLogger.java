package main.java.se.kth.iv1350.kassasystem.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The FileLogger class is responsible for logging messages to a file.
 */
public class FileLogger implements Logger {
    private PrintWriter logStream;

    /**
     * Constructs a new FileLogger object. It initializes the log stream to write to
     * the "error.log" file.
     * If an IOException were to occur during the file initialization, an error
     * message is
     * printed and the exception is logged.
     */
    public FileLogger() {
        try {
            logStream = new PrintWriter(new FileWriter("log.txt"), true);
        } catch (IOException IOException) {
            System.out.println("CAN NOT LOG.");
            IOException.printStackTrace();
        }
    }

    /**
     * Logs the specified message to the file.
     *
     * @param message The message to be logged.
     */
    @Override
    public void log(String message) {
        logStream.println(message);
    }
}
