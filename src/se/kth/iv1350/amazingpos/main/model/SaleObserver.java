package se.kth.iv1350.amazingpos.main.model;

/**
 * An interface for classes that want to be notified when a sale has been paid for.
 */
public interface SaleObserver {
    /**
     * Invoked when a sale has been paid for.
     * 
     * @param totalPrice The total price of the sale.
     */
    void updateSaleProfits(double totalPrice);
}
