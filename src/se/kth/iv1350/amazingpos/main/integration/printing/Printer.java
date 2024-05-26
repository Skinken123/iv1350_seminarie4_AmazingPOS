package se.kth.iv1350.amazingpos.main.integration.printing;

import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;

public interface Printer {
    /**
     * Prints the relevant information given by the dto object passed to the method.
     * 
     * @param receiptDTO the object containing all information from the sale.
     */
    public void print(ReceiptDTO receiptDTO);
}
