package main.java.se.kth.iv1350.kassasystem.startup;

import main.java.se.kth.iv1350.kassasystem.view.View;
import main.java.se.kth.iv1350.kassasystem.controller.Controller;
import main.java.se.kth.iv1350.kassasystem.integration.*;

public class Main {
    /**
     * Initiates the program and includes the main method, which launches the
     * application
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Printer printer = new Printer();
        ManagerForExternalInventorySystem managerForExternalInventorySystem = new ManagerForExternalInventorySystem();
        managerForExternalInventorySystem.setLogger(new FileLogger());
        ManagerForExternalAccountingSystem managerForExternalAccountingSystem = new ManagerForExternalAccountingSystem(
                0);
        ManagerForDiscounts managerForDiscounts = new ManagerForDiscounts();
        Controller contr = new Controller(printer, managerForDiscounts, managerForExternalInventorySystem,
                managerForExternalAccountingSystem);
        contr.setLogger(new FileLogger());
        View view = new View(contr);
        view.setLogger(new ConsoleLogger());
        view.runFakeExecution();
    }
}
