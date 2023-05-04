package test.model;

import main.java.se.kth.iv1350.kassasystem.model.*;
import java.util.*;
import java.time.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {
    private Sale newSale;
    private DTOForSale DTOForSale;

    @BeforeEach
    public void config() {
        newSale = new Sale();
        List<Product> products = null;
        double totalVAT = 0;
        double totalCost = 0;
        LocalTime time = LocalTime.now();
        DTOForSale = new DTOForSale(products, totalVAT, totalCost, time);
    }

    @Test
    public void testAddProductIsDescriptionCorrect() {
        System.out.println("Adding product by running addProduct");
        double cost = 59.90;
        double VAT = 14.975;
        String description = "Fläskytterfilé. Ca 1000 g";

        DTOForProduct DTOForProduct = new DTOForProduct(cost, VAT, description);
        int aProductIDThatIsNotYetOccupied = 10;
        int anyNumberOfProductsBiggerThanZero = 1;
        Product product = new Product(DTOForProduct, aProductIDThatIsNotYetOccupied, anyNumberOfProductsBiggerThanZero);
        int numberOfSetsOfProduct = 2;
        newSale.addProduct(numberOfSetsOfProduct, product);
        String getDescriptionOfAddedProduct = newSale.getOrderInfo().getProducts().get(0).getDTOForProduct()
                .getDescription();
        assertEquals(description, getDescriptionOfAddedProduct, "The result obtained was not the same as the expected result!");
    }

    @Test
    public void testAddProductIsVATIsCorrect() {
        System.out.println("Adding product by running addProduct and testing if the VAT is correct");
        double cost = 59.90;
        double VAT = 14.975;
        String description = "Fläskytterfilé. Ca 1000 g";

        DTOForProduct DTOForProduct = new DTOForProduct(cost, VAT, description);
        int aProductIDThatIsNotYetOccupied = 10;
        int anyNumberOfProductsBiggerThanZero = 1;
        Product product = new Product(DTOForProduct, aProductIDThatIsNotYetOccupied, anyNumberOfProductsBiggerThanZero);
        int numberOfSetsOfProduct = 2;
        newSale.addProduct(numberOfSetsOfProduct, product);
        double getVATOfAddedProduct = newSale.getOrderInfo().getProducts().get(0).getDTOForProduct().getVAT();
        assertEquals(VAT, getVATOfAddedProduct, "The result obtained was not the same as the expected result!");
    }

    @Test
    public void testAddProductIsPriceIsCorrect() {
        System.out.println("Adding product by running addProduct and testing if the price is correct");
        double cost = 59.90;
        double VAT = 14.975;
        String description = "Fläskytterfilé. Ca 1000 g";

        DTOForProduct DTOForProduct = new DTOForProduct(cost, VAT, description);
        int aProductIDThatIsNotYetOccupied = 10;
        int anyNumberOfProductsBiggerThanZero = 1;
        Product product = new Product(DTOForProduct, aProductIDThatIsNotYetOccupied, anyNumberOfProductsBiggerThanZero);
        int numberOfSetsOfProduct = 2;
        newSale.addProduct(numberOfSetsOfProduct, product);
        double getCostOfAddedProduct = newSale.getOrderInfo().getProducts().get(0).getDTOForProduct().getCost();
        assertEquals(cost, getCostOfAddedProduct, "The result obtained was not the same as the expected result!");
    }

    @Test
    public void testIfProductOfTheSameKindExistsForTwoIdenticalProducts() {
        System.out.println(
                "Testing if the amount of one type of product will increase if when we add a second, identical product");
        double cost = 59.90;
        double VAT = 14.975;
        String description = "Fläskytterfilé. Ca 1000 g";

        DTOForProduct DTOForProduct = new DTOForProduct(cost, VAT, description);
        int aProductIDThatIsNotOccupied = 10;
        int anyNumberOfProductsBiggerThanZero = 1;
        Product firstProduct = new Product(DTOForProduct,
                aProductIDThatIsNotOccupied,
                anyNumberOfProductsBiggerThanZero);
        Product secondProduct = new Product(DTOForProduct,
                aProductIDThatIsNotOccupied,
                anyNumberOfProductsBiggerThanZero);

        int numberOfSetsOfProduct = 1;
        newSale.addProduct(numberOfSetsOfProduct, firstProduct);
        newSale.addProduct(numberOfSetsOfProduct, secondProduct);
        int numberOfTheSameProduct = 2;
        int getNumberOfTheSameProduct = newSale.getNumberOfProducts().get(0);
        assertEquals(numberOfTheSameProduct, getNumberOfTheSameProduct, "The result obtained was not the same as the expected result!");
    }

    @Test
    public void testIfProductOfTheSameKindExistsForTwoProductsWithDifferentID() {
        System.out.println(
                "Testing that the amount of one type of product will NOT increase if when we add a second, product with a different ID");
        double cost = 59.90;
        double VAT = 14.975;
        String description = "Fläskytterfilé. Ca 1000 g";

        DTOForProduct DTOForProduct = new DTOForProduct(cost, VAT, description);
        int aProductIDThatIsNotOccupied = 10;
        int aSecondProductIDThatIsNotOccupied = 11;
        int anyNumberOfProductsBiggerThanZero = 1;
        Product firstProduct = new Product(DTOForProduct,
                aProductIDThatIsNotOccupied,
                anyNumberOfProductsBiggerThanZero);
        Product secondProduct = new Product(DTOForProduct,
                aSecondProductIDThatIsNotOccupied,
                anyNumberOfProductsBiggerThanZero);

        int numberOfSetsOfProduct = 1;
        newSale.addProduct(numberOfSetsOfProduct, firstProduct);
        newSale.addProduct(numberOfSetsOfProduct, secondProduct);
        int numberOfTheSameProduct = 1;
        int getNumberOfTheSameProduct = newSale.getNumberOfProducts().get(0);
        assertEquals(numberOfTheSameProduct, getNumberOfTheSameProduct, "The result obtained was not the same as the expected result!");
    }
}
