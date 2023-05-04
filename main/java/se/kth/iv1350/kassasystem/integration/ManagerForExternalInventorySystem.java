package main.java.se.kth.iv1350.kassasystem.integration;

import java.util.*;
import main.java.se.kth.iv1350.kassasystem.model.*;

/**
 * Manager for external inventory system which manages the inventory by faking
 * data store.
 */

public class ManagerForExternalInventorySystem {

    private List<DTOForProduct> DTOsForBusiness = new ArrayList<>();
    private List<Product> businessProducts = new ArrayList<>();

    public ManagerForExternalInventorySystem() {
    }

    /**
     * Returns all products of the store.
     * 
     * @return storeItems, All if the items in the store.
     */
    public List<Product> getProducts() {
        return businessProducts;
    }

    /**
     * Finds a requested product from the inventory with the help of a productID.
     * 
     * @param productID the id of the product.
     * @return Returns the product, if it exists in the inventory. In other case,
     *         null will be returned.
     */

    public Product find(int productID) {
        for (Product item : businessProducts) {
            if (productID == item.getProductID()) {
                return item;
            }
        }
        return null;
    }

    /**
     * Adjusts the inventory to reflect the items that were sold during a sale.
     * 
     * @param newSale The newSale which has the details about the sale.
     */

    public void update(Sale newSale) {
        List<Integer> itemsQuantity = newSale.getNumberOfProducts();
        List<Product> products = newSale.getProducts();
        Map<Product, Integer> quantityMap = createQuantityMap(products, itemsQuantity);
        for (Product product : businessProducts) {
            if (quantityMap.containsKey(product)) {
                int quantity = quantityMap.get(product);
                product.modifyNumberOfItems(quantity);
            }
        }
    }

    /*
     * Will create quantityMap, a map that associates each Product object in the
     * sale with its
     * corresponding quantity
     * 
     * @param The newSale which has the details about the sale.
     * 
     * @return Returns quantityMap, a map that associates each Product object in the
     * sale with its
     * corresponding quantity
     */

    private static Map<Product, Integer> createQuantityMap(List<Product> products, List<Integer> itemsQuantity) {
        Map<Product, Integer> quantityMap = new HashMap<>();
        for (int i = 0; i < products.size(); i++) {
            quantityMap.put(products.get(i), itemsQuantity.get(i));
        }
        return quantityMap;
    }

    /**
     * Adds products to the inventory by using identifier, number of items and
     * DTOForProduct. Since we start without products, it starts with creating DTOs
     * for new products with cost, VAT sum and description.
     */

    public void addProducts() {
        DTOsForBusiness.add(new DTOForProduct(19.95, 4.9875, "Morötter Klass 1, 1kg"));
        businessProducts.add(new Product(DTOsForBusiness.get(0), 0, 5));
        DTOsForBusiness.add(new DTOForProduct(20.95, 5.2375, "Mellanmjölk Längre Hållbarhet 1,5%"));
        businessProducts.add(new Product(DTOsForBusiness.get(1), 1, 10));
    }
}
