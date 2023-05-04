package main.java.se.kth.iv1350.kassasystem.model;

/*
 * Represents a product
 */

public class Product {

    private DTOForProduct DTOForProduct;
    private int productID;
    private int number;

    /**
     * Creates a new Product object.
     * 
     * @param DTOForProduct groups together the properties of a product
     * @param productID     the unique ID of the product
     * @param number        the number of items being sold
     */
    public Product(DTOForProduct DTOForProduct, int productID, int number) {
        this.DTOForProduct = DTOForProduct;
        this.productID = productID;
        this.number = number;
    }

    /**
     * Returns the product DTOForProduct.
     * 
     * @return the DTOForProduct of the product.
     */
    public DTOForProduct getDTOForProduct() {
        return DTOForProduct;
    }

    /**
     * Returns the unique ID of the product.
     * 
     * @return the product ID.
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Returns the number of items being sold.
     * 
     * @return the number of items.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Modifies the number of items
     * 
     * @param number the number of items that will be removed.
     */
    public void modifyNumberOfItems(int number) {
        this.number -= number;
    }
}
