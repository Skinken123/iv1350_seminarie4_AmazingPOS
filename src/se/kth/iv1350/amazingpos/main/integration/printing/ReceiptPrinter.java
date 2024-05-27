package se.kth.iv1350.amazingpos.main.integration.printing;

import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;

/**
 * Represents a class which will handle communication with the receipt printer.
 */

public class ReceiptPrinter {
    /**
     * Calls the receipt printer to print the receipt. This method will also print the receipt to the console to give feedback to the developer.
     * 
     * @param receipt the object containing all information that needs to be on the receipt.
     */
    public void printReceipt(ReceiptDTO receiptDTO) {
        PrinterFactory printerFactory = PrinterFactory.getPrinterFactory();
        printerFactory.getDefaultPrinter().print(receiptDTO);
    }
}
