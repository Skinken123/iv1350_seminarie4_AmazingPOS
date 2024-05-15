package se.kth.iv1350.amazingpos.main.integration;

import se.kth.iv1350.amazingpos.main.model.dto.ItemDTO;
import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;

/**
 * Represents a class which will handle communication with the receipt printer.
 */

public class ReceiptPrinter {
    /**
     * Calls the receipt printer to print the receipt. This method will also print the receipt to the console to give feedback to the view.
     * 
     * @param receipt the object containing all information that needs to be on the receipt.
     */
    public void printReceipt(ReceiptDTO receiptDTO) {
        System.out.println("Receipt: ");
        System.out.println("Sale time: " + receiptDTO.getSaleTime());
        for (ItemDTO item : receiptDTO.getCurrentItemList()) {
            System.out.println(item.getItemName() + "\t" + item.getQuantity() + " x " + item.getPrice() + "\t" + (item.getPrice()*item.getQuantity()) + " SEK");
        }
        System.out.println("\n");
        System.out.println("Total price (including VAT): " + receiptDTO.getTotalPrice() + " SEK");
        System.out.println("Total VAT:   " + receiptDTO.getTotalVAT()+ "  SEK" + "\n");
        System.out.println("Payment: " + receiptDTO.getPayment() + " SEK");
        System.out.println("Change: " + receiptDTO.getChange() + " SEK");
    }
}
