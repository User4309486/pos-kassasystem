package main.java.se.kth.iv1350.kassasystem.integration;

import main.java.se.kth.iv1350.kassasystem.model.*;

/**
 * Manager for external accounting system which manages the accounting
 */

public class ManagerForExternalAccountingSystem {
    private int businessMoney;

    public ManagerForExternalAccountingSystem(int businessMoney) {
        this.businessMoney = businessMoney;
    }

    /**
     * Returns the stored amount of money in this business.
     * 
     * @return returns businessMoney
     */
    public int getBusinessBalance() {
        return businessMoney;
    }

    /**
     * Modifies the stored amount of money in this business depending on input.
     * 
     * @param money sum of money that should be added
     */
    public void modify(double money) {
        businessMoney += money;
    }

    /*
     * Recieves sale information in order to update accounting. We leave it like
     * this.
     * 
     * @param newSale the sale and associated information.
     */

    public void sendSaleInformation(Sale newSale) {
    }

}