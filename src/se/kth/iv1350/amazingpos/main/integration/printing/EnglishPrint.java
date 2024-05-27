package se.kth.iv1350.amazingpos.main.integration.printing;

import se.kth.iv1350.amazingpos.main.model.dto.ItemDTO;
import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;

/**
 * Represents a class which will print the receipt in english.
 */
class EnglishPrint implements Printer{
    EnglishPrint() {
    }
    /**
     * Prints an english version of the receipt.
     */
    @Override
    public void print(ReceiptDTO receiptDTO) {
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
