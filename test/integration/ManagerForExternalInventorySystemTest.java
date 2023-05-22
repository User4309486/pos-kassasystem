package test.integration;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.se.kth.iv1350.kassasystem.integration.*;
import main.java.se.kth.iv1350.kassasystem.model.*;

public class ManagerForExternalInventorySystemTest {

    private ManagerForExternalInventorySystem managerForExternalInventorySystem;

    @BeforeEach
    public void setUp() {
        managerForExternalInventorySystem = new ManagerForExternalInventorySystem();
        managerForExternalInventorySystem.addProducts();
    }

    @Test
    public void testGetProducts() {
        System.out.println("GetProducts method");
        int expectedSize = 2;
        assertEquals(expectedSize, managerForExternalInventorySystem.getProducts().size(),
                "The result obtained was not the same as the expected result.");
    }

    @Test
    public void testFindExistingProduct() {
        System.out.println("Find method with an existing product");
        int productID = 0;
        Product product;
        try {
            product = managerForExternalInventorySystem.find(productID);
            assertNotNull(product, "The result obtained was null, expected a product.");
        } catch (InvalidProductIDException | DatabaseAccessException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testFindNonExistingProduct() {
        System.out.println("Find method with a non-existing product");
        int productID = 457;
        assertThrows(InvalidProductIDException.class, () -> {
            managerForExternalInventorySystem.find(productID);
        });
    }

    @Test
    public void testFindProductWithDatabaseAccessException() {
        System.out.println("Find method with a database access exception");
        int productID = -1;
        assertThrows(DatabaseAccessException.class, () -> {
            managerForExternalInventorySystem.find(productID);
        });
    }

    @Test
    public void testUpdate() {
        System.out.println("Update method");
        Sale sale = new Sale();
        sale.addProduct(2, managerForExternalInventorySystem.getProducts().get(0));
        sale.addProduct(1, managerForExternalInventorySystem.getProducts().get(1));

        managerForExternalInventorySystem.update(sale);

        int expectedQuantity1 = 3;
        int expectedQuantity2 = 9;

        assertEquals(expectedQuantity1, managerForExternalInventorySystem.getProducts().get(0).getNumber(),
                "The result obtained was not the same as the expected result.");
        assertEquals(expectedQuantity2, managerForExternalInventorySystem.getProducts().get(1).getNumber(),
                "The result obtained was not the same as the expected result.");
    }

    @Test
    public void testAddProducts() {
        System.out.println("Testing addProducts method");
        managerForExternalInventorySystem.addProducts();
        int expectedSize = 4;
        assertEquals(expectedSize, managerForExternalInventorySystem.getProducts().size(),
                "The result obtained was not the same as the expected result.");
    }
}
