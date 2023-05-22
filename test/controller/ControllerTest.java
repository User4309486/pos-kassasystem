import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.se.kth.iv1350.kassasystem.controller.*;
import main.java.se.kth.iv1350.kassasystem.integration.*;
import main.java.se.kth.iv1350.kassasystem.model.*;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    private Controller contr;
    private Printer printer;
    private ManagerForExternalInventorySystem managerForExternalInventorySystem;
    private ManagerForExternalAccountingSystem managerForExternalAccountingSystem;
    private ManagerForDiscounts managerForDiscounts;

    @BeforeEach
    public void config() {
        printer = new Printer();
        managerForDiscounts = new ManagerForDiscounts();
        managerForExternalInventorySystem = new ManagerForExternalInventorySystem();
        managerForExternalAccountingSystem = new ManagerForExternalAccountingSystem(100);
        managerForExternalInventorySystem.addProducts();
        managerForExternalInventorySystem.setLogger(new FileLogger());
        contr = new Controller(printer, managerForDiscounts, managerForExternalInventorySystem,
                managerForExternalAccountingSystem);
        contr.setLogger(new FileLogger());
    }

    @Test
    public void testInputProduct() {
        System.out.println("A buyable product is entered into inputProduct.");
        contr.beginSale();
        int maximumBuyableAmountOfFirstProduct = 5;
        int productID = 0;
        String descriptionOfFirstProductAdded = "Morötter Klass 1, 1kg";
        String getFirstDescription = "";
        try {
            DTOForSale DTOForSale = contr.inputProduct(maximumBuyableAmountOfFirstProduct, productID);
            getFirstDescription = DTOForSale.getProducts().get(0).getDTOForProduct().getDescription();
            assertEquals(descriptionOfFirstProductAdded, getFirstDescription,
                    "The result obtained was not the same as the expected result.");
        } catch (Exception exception) {
            fail("One exception has been thrown, " + exception.getMessage());
        }

    }

    @Test
    public void testScanItemWithNonExistentID() {
        System.out.println("A non-exisistent product (null) is entered into inputProduct.");
        contr.beginSale();

        int number = 2;
        int productID = 4;
        String nonExisistentProduct = "Invalid product ID: " + productID;

        try {
            DTOForSale newNullProduct = contr.inputProduct(number, productID);
            contr.endSale();
            fail("Error, no exception has been thrown.");
        } catch (InvalidProductIDException exception) {
            assertEquals(
                    exception.getMessage(),
                    nonExisistentProduct,
                    "The result obtained was not the same as the expected result.");
        } catch (Exception exception) {
            fail("One exception has been thrown, " + exception.getMessage());
        }
    }

    @Test
    public void testEndSale() {
        System.out.println("Ending sale with endSale. We remove 2 sold objects.");
        contr.beginSale();
        int numberOfproductsOfThisTypeInStock = 5;
        int soldProducts = 2;
        int remainingProducts = numberOfproductsOfThisTypeInStock - soldProducts;
        int productID = 0;
        try {
            DTOForSale DTOForSale = contr.inputProduct(soldProducts, productID);
            contr.endSale();
            int getRemainingProducts = DTOForSale.getProducts().get(0).getNumber();
            assertEquals(remainingProducts, getRemainingProducts,
                    "The result obtained was not the same as the expected result!");
        } catch (Exception exception) {
            fail("One exception has been thrown, " + exception.getMessage());
        }
    }

    @Test
    public void testPayOverSum() {
        System.out.println("Paying a larger sum than required in pay");
        contr.beginSale();
        try {
            int number = 1;
            int productID = 0;
            contr.inputProduct(number, productID);
            contr.endSale();
            double costOfFirstProductAddedIncludingTax = 24.9375;
            double amountBeingPaid = 50;
            double expectedChange = amountBeingPaid - costOfFirstProductAddedIncludingTax;
            String paymentOption = "Cash";
            int customerID = 1234;
            double change = contr.pay(amountBeingPaid, paymentOption, customerID);
            assertEquals(expectedChange, change, "The result obtained was not the same as the expected result!");
        } catch (Exception exception) {
            fail("One exception has been thrown, " + exception.getMessage());
        }
    }

    @Test
    public void testPayLessThanSum() {
        System.out.println("Paying too little in pay");
        contr.beginSale();
        try {
            int number = 1;
            int productID = 0;
            contr.inputProduct(number, productID);
            contr.endSale();
            double costOfFirstProductAddedIncludingTax = 24.9375;
            double amountBeingPaid = 10;
            double expectedChange = amountBeingPaid - costOfFirstProductAddedIncludingTax;
            String paymentOption = "Cash";
            int customerID = 1234;
            double change = contr.pay(amountBeingPaid, paymentOption, customerID);
            assertEquals(expectedChange, change, "The result obtained was not the same as the expected result!");
        } catch (Exception exception) {
            fail("One exception has been thrown, " + exception.getMessage());
        }
    }

    @Test
    public void testPayCorrectSum() {
        try {
            System.out.println("Paying the correct sum in pay");
            contr.beginSale();
            int number = 1;
            int productID = 0;
            contr.inputProduct(number, productID);
            contr.endSale();
            double costOfFirstProductAddedIncludingTax = 24.9375;
            double amountBeingPaid = 24.9375;
            double expectedChange = amountBeingPaid - costOfFirstProductAddedIncludingTax;
            String paymentOption = "Cash";
            int customerID = 1234;
            double change = contr.pay(amountBeingPaid, paymentOption, customerID);
            assertEquals(expectedChange, change, "The result obtained was not the same as the expected result!");
        } catch (Exception exception) {
            fail("One exception has been thrown, " + exception.getMessage());
        }
    }
}
