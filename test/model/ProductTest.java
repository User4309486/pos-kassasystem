package test.model;

import main.java.se.kth.iv1350.kassasystem.model.Product;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import main.java.se.kth.iv1350.kassasystem.integration.*;

public class ProductTest {
    private Product product;
    private ManagerForExternalInventorySystem managerForExternalInventorySystem = new ManagerForExternalInventorySystem();

    @Test
    public void testUpdateQuantity() {
        System.out.println("Removing products by calling modifyNumberOfItems with a specified number");
        managerForExternalInventorySystem = new ManagerForExternalInventorySystem();
        managerForExternalInventorySystem.addProducts();
        int productsToRemove = 3;
        int NumberOfTheFirstProduct = 5;
        int numberOfProductsThatShouldRemain = NumberOfTheFirstProduct - productsToRemove;
        product = managerForExternalInventorySystem.getProducts().get(0);
        System.out.println(managerForExternalInventorySystem.getProducts().get(0).getNumber());
        product.modifyNumberOfItems(productsToRemove);
        System.out.println(managerForExternalInventorySystem.getProducts().get(0).getNumber());
        int remainingNumberOfProducts = managerForExternalInventorySystem.getProducts().get(0).getNumber();
        assertEquals(numberOfProductsThatShouldRemain,
                remainingNumberOfProducts, "The result obtained was not the same as the expected result");
    }
}
