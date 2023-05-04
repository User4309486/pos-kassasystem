package main.java.se.kth.iv1350.kassasystem.controller;

import main.java.se.kth.iv1350.kassasystem.integration.*;
import main.java.se.kth.iv1350.kassasystem.model.*;

/*
 * This is the application's only controller. All calls to the model pass through this class.
 */

public class Controller {

    private Sale newSale;
    private Printer printer;
    private ManagerForExternalInventorySystem managerForExternalInventorySystem;
    private ManagerForExternalAccountingSystem managerForExternalAccountingSystem;
    private ManagerForDiscounts managerForDiscounts;
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
     * Begins the sale by creating a new sale object.
     * 
     * @return getOrderInfo will give the information about the order
     */

    public DTOForSale beginSale() {
        newSale = new Sale();
        return newSale.getOrderInfo();
    }

    /**
     * Adds a product to the new sale.
     * 
     * @param number    The number of the requested product.
     * @param productID The identification of the product.
     * @return DTOForSale, should be visible on the display in view.
     */

    public DTOForSale inputProduct(int number, int productID) {
        Product product = managerForExternalInventorySystem.find(productID);
        if (product == null || product.getNumber() < number) {
            return null;
        }
        newSale.addProduct(number, product);
        return newSale.getOrderInfo();
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
     * @param customerID     the id for the customer
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

}
