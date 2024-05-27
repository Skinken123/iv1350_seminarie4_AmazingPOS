package se.kth.iv1350.amazingpos.main.integration.printing;

import se.kth.iv1350.amazingpos.main.model.dto.ItemDTO;
import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;

/**
 * Represents a class which will print the receipt in swedish.
 */
class SwedishPrint implements Printer{
    SwedishPrint() {
    }
    /**
     * Prints an swedish version of the receipt.
     */
    @Override
    public void print(ReceiptDTO receiptDTO) {
        System.out.println("Kvitto: ");
        System.out.println("Tid vid köp: " + receiptDTO.getSaleTime());
        for (ItemDTO item : receiptDTO.getCurrentItemList()) {
            System.out.println(item.getItemName() + "\t" + item.getQuantity() + " x " + item.getPrice() + "\t" + (item.getPrice()*item.getQuantity()) + " SEK");
        }
        System.out.println("\n");
        System.out.println("Totalt pris (inkluderande moms): " + receiptDTO.getTotalPrice() + " SEK");
        System.out.println("Total moms:   " + receiptDTO.getTotalVAT()+ "  SEK" + "\n");
        System.out.println("Betalning: " + receiptDTO.getPayment() + " SEK");
        System.out.println("Växel: " + receiptDTO.getChange() + " SEK");
    }
}
