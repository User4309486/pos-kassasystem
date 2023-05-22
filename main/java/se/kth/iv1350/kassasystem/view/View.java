package main.java.se.kth.iv1350.kassasystem.view;

import main.java.se.kth.iv1350.kassasystem.model.*;

import main.java.se.kth.iv1350.kassasystem.controller.*;
import main.java.se.kth.iv1350.kassasystem.integration.DatabaseAccessException;
import main.java.se.kth.iv1350.kassasystem.integration.InvalidProductIDException;
import main.java.se.kth.iv1350.kassasystem.integration.Logger;

/**
 * "The program's View initiates a runFakeExecution task."
 */

public class View {
        private Controller contr;
        private Logger logger;

        /**
         * Instantiates a new object that utilizes the designated controller for all
         * communications with other layers
         * 
         * @param contr The designated controller for all communications with other
         *              layers.
         */

        public View(Controller contr) {
                this.contr = contr;
                contr.registerObserverForSales(new TotalRevenueFileOutput());
                contr.registerObserverForSales(new TotalRevenueView());
        }

        /*
         * Initiates the logger for logging purposes.
         * 
         * @param logger The logger object to be set.
         * 
         */

        public void setLogger(Logger logger) {
                this.logger = logger;
        }

        /**
         * Initiate a sale by invoking all system operations in the controller.
         */

        public void runFakeExecution() {
                contr.beginSale();
                System.out.println("Sale was started.");
                int numberOfProducts = 1;
                DTOForSale DTOForSale;
                try {
                        DTOForSale = contr.inputProduct(numberOfProducts, 0);
                        System.out.println("Products: "
                                        + DTOForSale.getProducts().get(0).getDTOForProduct().getDescription()
                                        + " "
                                        + numberOfProducts + " st"
                                        + " " + DTOForSale.getProducts().get(0).getDTOForProduct().getCost());
                        System.out.println("Total: " + DTOForSale.getTotalCost());
                        numberOfProducts = 3;
                        DTOForSale = contr.inputProduct(numberOfProducts, 1);
                        System.out.println("Products: "
                                        + DTOForSale.getProducts().get(1).getDTOForProduct().getDescription()
                                        + " " + numberOfProducts + " st"
                                        + " " + DTOForSale.getProducts().get(1).getDTOForProduct().getCost());
                        System.out.println("Total: " + DTOForSale.getTotalCost());

                }

                catch (DatabaseAccessException exception) {
                        logger.log("We can't reach the database. Please contact IT.");
                } catch (InvalidProductIDException exception) {
                        logger.log("The requested product does not exist. Input another product, or contact IT.");
                }

                DTOForSale = contr.endSale();
                double cost = contr.endSale().getTotalCost();
                String paymentOption = "Cash";
                int customerID = 1234;
                double change = contr.pay(cost, paymentOption, customerID);

                System.out.println("Change:              " + change);
                contr.print();
        }
}
