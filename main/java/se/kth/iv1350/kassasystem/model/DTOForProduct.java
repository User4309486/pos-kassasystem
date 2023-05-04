package main.java.se.kth.iv1350.kassasystem.model;

/**
 * Groups together the properties of a product.
 */

public class DTOForProduct {

    private double cost;
    private double VAT;
    private String description;

    /**
     * Will create an object of DTOForProduct
     * 
     * @param cost        the cost of the product
     * @param VAT         the applicable tax of the product
     * @param description the description of a product
     */
    public DTOForProduct(double cost, double VAT, String description) {
        this.cost = cost;
        this.VAT = VAT;
        this.description = description;
    }

    /*
     * Gives the cost of the product
     * 
     * @return cost of the product
     */

    public double getCost() {
        return cost;
    }

    /*
     * Gives the applicable tax of the product
     * 
     * @return the applicable tax of the product
     */

    public double getVAT() {
        return VAT;
    }

    /*
     * Gives the description of the product
     * 
     * @return the description of the product
     */

    public String getDescription() {
        return description;
    }

}
