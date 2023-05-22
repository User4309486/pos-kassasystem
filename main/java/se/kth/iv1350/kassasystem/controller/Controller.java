package main.java.se.kth.iv1350.kassasystem.controller;

import main.java.se.kth.iv1350.kassasystem.integration.*;
import main.java.se.kth.iv1350.kassasystem.model.*;
import java.util.*;

/*
 * This is the application's only controller. All calls to the model pass through this class.
 */

public class Controller {
    private Sale newSale;
    private Printer printer;
    private ManagerForExternalInventorySystem managerForExternalInventorySystem;
    private ManagerForExternalAccountingSystem managerForExternalAccountingSystem;
    private ManagerForDiscounts managerForDiscounts;
    private List<ObserverForSales> observersForSales = new ArrayList<>();
    private Logger logger;

    /*
     * The constructor below creates an instance of this Controller.
     * 
     * @param printer external printer for the reciepts
     * 
     * @param managerForDiscounts external manager for discounts *
     * 
     * @param managerForExternalInventorySystem external manager for the inventory
     * system
     * 
     * @param ManagerForExternalAccountingSystem external manager for the accounting
     * system
     */

    public Controller(Printer printer, ManagerForDiscounts managerForDiscounts,
            ManagerForExternalInventorySystem managerForExternalInventorySystem,
            ManagerForExternalAccountingSystem managerForExternalAccountingSystem) {
        this.printer = printer;
        this.managerForDiscounts = managerForDiscounts;
        this.managerForExternalInventorySystem = managerForExternalInventorySystem;
        this.managerForExternalAccountingSystem = managerForExternalAccountingSystem;
        managerForExternalInventorySystem.addProducts();
    }
    /*
     * Initiates the logger for logging purposes.
     * 
     * @param logger The logger object to be set.
     * 
     */

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    /*
     * Begins the sale by creating a new sale object.
     * 
     * @return getOrderInfo will give the information about the order
     */

    public DTOForSale beginSale() {
        newSale = new Sale();
        for (ObserverForSales observer : observersForSales) {
            newSale.addObserverForSales(observer);
        }
        return newSale.getOrderInfo();
    }

    /**
     * Adds a product to the new sale.
     * 
     * @param number    The number of the requested product.
     * @param productID The identification of the product.
     * @return DTOForSale, should be visible on the display in view.
     * @throws DatabaseAccessException   is thrown when the database can't be
     *                                   reached.
     * @throws InvalidProductIDException is thrown if the productID passed does not
     *                                   correspond to a valid product in the
     *                                   external inventory system.
     */
    public DTOForSale inputProduct(int number, int productID)
            throws DatabaseAccessException, InvalidProductIDException {
        try {
            Product product = managerForExternalInventorySystem.find(productID);
            if (number <= product.getNumber()) {
                newSale.addProduct(number, product);
            }
            return newSale.getOrderInfo();
        } catch (DatabaseAccessException exception) {
            logger.log("Error! Database access failed.");
            throw exception;
        } catch (InvalidProductIDException exception) {
            logger.log("Error! Invalid product ID. Please input a valid product ID, instead of: " + productID);
            throw exception;
        }
    }

    /**
     * Ends the sale.
     * 
     * @return gives DTOForSale for the sale.
     */

    public DTOForSale endSale() {
        managerForExternalInventorySystem.update(newSale);
        return newSale.getOrderInfo();
    }

    /**
     * Initializes discounts by running the constructor DTO for discounts..
     * 
     * @param customerID the id for the customer
     * @return new DTOForDiscount(customerID) gives DTO for discounts.
     */

    public DTOForDiscount initializeDiscounts(int customerID) {
        return new DTOForDiscount(customerID);
    }

    /**
     * Manages the payment process, updates the accounting system, and gives back
     * 
     * @param paid          how much was actually paid
     * @param paymentOption the way the payment was made
     * @param customerID    the ID of the customer
     * @return change, the amount that will be given back to the customer.
     */
    public double pay(double paid, String paymentOption, int customerID) {
        double totalPrice = calculateTotalPrice();
        double closingPrice = totalPrice * (1 - calculateDiscount(customerID));
        double change = paid - closingPrice;
        if (change >= 0) {
            managerForExternalAccountingSystem.modify(closingPrice);
            managerForExternalAccountingSystem.sendSaleInformation(newSale);
        }

        return change;
    }

    /**
     * Calculates the total price of the sale.
     * 
     * @return the total price of the sale.
     */
    public double calculateTotalPrice() {
        return newSale.getOrderInfo().getTotalCost();
    }

    /**
     * Calculates the discount for the sale.
     * 
     * @param customerID the ID of the customer.
     * @return the discount for the sale.
     */
    public double calculateDiscount(int customerID) {
        return managerForDiscounts.findDiscount(endSale(), initializeDiscounts(customerID));
    }

    /**
     * Printer will print the receipt for the new sale.
     */
    public void print() {
        printer.print(newSale.getReceipt());
    }

    /**
     * Adds an observer to be alerted when a new sale is made.
     *
     * @param observer The variable of the observer to be alerted.
     */
    public void registerObserverForSales(ObserverForSales observer) {
        observersForSales.add(observer);
    }
}
