package se.kth.iv1350.amazingpos.main.integration;

import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;


/**
 * Represents a class which will handle communication with the external accounting system.
 */
public class ExternalAccountingSystem {
    /**
     * Sends the sale information to the external accounting system after the sale has been ended.
     * To simulate getting a confirmation from the external accounting system, a message is printed to the console
     * 
     * @param receiptInfo The information about the sale.
     */
    public void updateAccountingSystem(ReceiptDTO receiptInfo) {
        System.out.println("Accounting system updated.");
    }
}
