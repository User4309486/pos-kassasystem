package main.java.se.kth.iv1350.kassasystem.integration;

import main.java.se.kth.iv1350.kassasystem.model.*;

/**
 * Responsible for communicating with an external printer which it sends info
 * to.
 */

public class Printer {
    public Printer() {
    }

    /**
     * Print a receipt, which essentially writes out a command.
     * 
     * @param receipt The relevant instance of recepit.
     */
    public void print(Receipt receipt) {
        System.out.println("Printer is printing receipt.");
    }
}