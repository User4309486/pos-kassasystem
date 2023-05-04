/**

This class represents a data transfer object (DTO) for a discount. It contains information about the customer
and a list of product IDs that are eligible for the discount.
*/
package main.java.se.kth.iv1350.kassasystem.integration;

import java.util.*;

public class DTOForDiscount {
    private int customerID;
    private List<Integer> productIDs;

    /**
     * Creates a new DTOForDiscount object with the given customer ID and list of
     * product IDs.
     * 
     * @param customerID The ID of the customer who is eligible for the discount.
     * @param productIDs A list of product IDs that are eligible for the discount.
     */
    public DTOForDiscount(int customerID) {
        this.customerID = customerID;
    }

    /**
     * Returns the ID of the customer who is eligible for the discount.
     * 
     * @return The customer ID.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Returns the list of product IDs that are eligible for the discount.
     * 
     * @return The list of product IDs.
     */
    public List<Integer> getProductIDs() {
        return productIDs;
    }
}