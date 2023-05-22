package main.java.se.kth.iv1350.kassasystem.model;

/**
 * The ObserverForSales interface represents an observer for sales in the pos.
 * Classes implementing this interface can register as observers to receive
 * notifications about new sales.
 */
public interface ObserverForSales {
    /**
     * Notifies the observer about a new sale with the specified total cost.
     *
     * @param totalCost The total cost of the sale.
     */
    public void newSale(double totalCost);
}