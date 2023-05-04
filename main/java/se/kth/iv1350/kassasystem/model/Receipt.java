package main.java.se.kth.iv1350.kassasystem.model;

import java.time.*;
import java.util.*;

/**
 * The recepit of the sale.
 */

public class Receipt {
    private DTOForSale orderInfo;
    private String businessName;
    private double totalVAT;
    private double totalCost;
    private LocalTime time;
    private double totalDiscount;
    private List<Product> products;

    /**
     * Creates a new Receipt object with the specified order information.
     * 
     * @param orderInfo the DTOForSale containing the order information
     */
    Receipt(DTOForSale orderInfo) {
        this.orderInfo = orderInfo;
        getInformationFromOrder();
    }

    /**
     * Retrieves the necessary information from the order information.
     */
    public void getInformationFromOrder() {
        businessName = orderInfo.getBusinessName();
        totalVAT = orderInfo.getTotalVAT();
        totalCost = orderInfo.getTotalCost();
        time = orderInfo.getTime();
        products = orderInfo.getProducts();
    }

    /**
     * Returns the business name.
     * 
     * @return the name of the business.
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * Returns the total VAT.
     * 
     * @return the total value added tax.
     */
    public double getTotalVAT() {
        return totalVAT;
    }

    /**
     * Returns the total cost.
     * 
     * @return the total cost of the sale.
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Returns the time of the sale.
     * 
     * @return the time of the sale.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns the total discount.
     * 
     * @return the total discount.
     */
    public double getTotalDiscount() {
        return totalDiscount;
    }

    /**
     * Returns the list of products in the order.
     * 
     * @return the list of products.
     */
    public List<Product> getNameOfItems() {
        return products;
    }
}