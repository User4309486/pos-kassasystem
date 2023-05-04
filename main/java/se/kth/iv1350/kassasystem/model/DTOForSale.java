package main.java.se.kth.iv1350.kassasystem.model;

import java.time.*;
import java.util.*;

/**
 * Groups together the properties of a new Sale.
 */

public class DTOForSale {
    private java.lang.String businessName = "Testbutik";
    private List<Product> products;
    private double totalVAT;
    private double totalCost;
    private LocalTime time;

    /**
     * Will create an object of DTOForProduct
     * 
     * @param products  the items to be sold
     * @param totalVAT  the total tax
     * @param totalCost the current total cost
     * @param time      stores the present time of the sale
     */
    public DTOForSale(List<Product> products, double totalVAT, double totalCost, LocalTime time) {
        this.products = products;
        this.totalVAT = totalVAT;
        this.totalCost = totalCost;
        this.time = time;
    }

    /**
     * Returns the name of the business.
     * 
     * @return the business name.
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * Returns the array of products being sold.
     * 
     * @return the array of products.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Returns the total value added tax for the sale.
     * 
     * @return the total VAT.
     */
    public double getTotalVAT() {
        return totalVAT;
    }

    /**
     * Returns the current total cost of the sale.
     * 
     * @return the total cost.
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Returns the present time of the sale.
     * 
     * @return the current time.
     */
    public LocalTime getTime() {
        return time;
    }
}
