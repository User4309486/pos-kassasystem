package main.java.se.kth.iv1350.kassasystem.view;

import java.io.*;
import main.java.se.kth.iv1350.kassasystem.model.ObserverForSales;;

/**
 * Implements SaleObserver and keeps track of the total revenue and writes it to
 * a file.
 */
public class TotalRevenueFileOutput implements ObserverForSales {
    private double totalRevenue;
    private PrintWriter file;

    /**
     * Creates a new text file, revenue
     */
    TotalRevenueFileOutput() {
        try {
            this.file = new PrintWriter(new FileWriter("revenue.txt"), true);
        } catch (IOException exception) {
            System.out.println("Failed to create file");
        }
    }

    /**
     * Updates the total revenue and writes it to the text file
     * 
     * @param totalPrice the price of the sale with VAT
     */
    @Override
    public void newSale(double totalPrice) {
        this.totalRevenue += totalPrice;
        this.file.println("Total Revenue: " + this.totalRevenue);
    }
}