package main.java.se.kth.iv1350.kassasystem.view;

import main.java.se.kth.iv1350.kassasystem.model.*;
import main.java.se.kth.iv1350.kassasystem.controller.*;

/**
 * "The program's View initiates a runFakeExecution task."
 */

public class View {
        private Controller contr;

        /**
         * Instantiates a new object that utilizes the designated controller for all
         * communications with other layers
         * 
         * @param contr The designated controller for all communications with other
         *              layers.
         */

        public View(Controller contr) {
                this.contr = contr;
        }

        /**
         * Initiate a sale by invoking all system operations in the controller.
         */

        public void runFakeExecution() {
                contr.beginSale();
                System.out.println("Sale was started.");
                int numberOfGoods = 1;
                DTOForSale DTOForSale = contr.inputProduct(numberOfGoods, 0);
                System.out.println("Products: " + DTOForSale.getProducts().get(0).getDTOForProduct().getDescription()
                                + " "
                                + numberOfGoods + " st"
                                + " " + DTOForSale.getProducts().get(0).getDTOForProduct().getCost());
                System.out.println("Total: " + DTOForSale.getTotalCost());
                numberOfGoods = 3;
                DTOForSale = contr.inputProduct(numberOfGoods, 1);
                System.out.println("Products: " + DTOForSale.getProducts().get(1).getDTOForProduct().getDescription()
                                + " " + numberOfGoods + " st"
                                + " " + DTOForSale.getProducts().get(1).getDTOForProduct().getCost());
                System.out.println("Total: " + DTOForSale.getTotalCost());

                DTOForSale = contr.endSale();
                double cost = contr.endSale().getTotalCost();
                String paymentOption = "Cash";
                int customerID = 1234;
                double change = contr.pay(cost, paymentOption, customerID);

                System.out.println("Change:              " + change);
                contr.print();
        }
}
