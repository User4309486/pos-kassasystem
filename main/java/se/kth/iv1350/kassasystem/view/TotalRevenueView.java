package main.java.se.kth.iv1350.kassasystem.view;

import main.java.se.kth.iv1350.kassasystem.model.ObserverForSales;

/**
 * Implements SaleObserver and keeps track of the total revenue and prints it to
 * the console.
 */
class TotalRevenueView implements ObserverForSales {
    private double totalRevenue;

    /**
     * Updates the total revenue and prints it to the console.
     * 
     * @param totalPrice the price of the sale with VAT
     */
    @Override
    public void newSale(double totalPrice) {
        totalRevenue += totalPrice;
        System.out.println("Total revenue: " + totalRevenue);
    }
}