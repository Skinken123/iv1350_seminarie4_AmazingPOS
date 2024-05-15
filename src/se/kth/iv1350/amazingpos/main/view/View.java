package se.kth.iv1350.amazingpos.main.view;

import java.text.DecimalFormat;
import java.util.ResourceBundle.Control;

import se.kth.iv1350.amazingpos.main.controller.Controller;
import se.kth.iv1350.amazingpos.main.model.dto.ItemDTO;
import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;

/**
 * Represents the view of the program. This class represents the user interface which we will not code in this project.
 * Instead, we will simulate a user interface by calling the controller with predefined calls. And print the results to the console.
 */

public class View {
    private Controller contr;

    /**
     * Creates a new instance of the view.
     * 
     * @param contr The controller that is used for all calls from the view.
     */
    public View(Controller contr) {
        this.contr = contr;
    }

    /**
     * Simulates a user input that generates calls to all system operations.
     */
    public void sampleExecution() {
        contr.startSale();
        System.out.println("A new sale has been started!" + "\n");

        ReceiptDTO currReceiptDTO = contr.enterNewItem(1);
        printNewItem(currReceiptDTO,1);
        currReceiptDTO = contr.enterNewItem(2);
        printNewItem(currReceiptDTO,2);
        currReceiptDTO = contr.enterNewItem(1);
        printNewItem(currReceiptDTO,1);
        currReceiptDTO = contr.enterNewItem(3);
        printNewItem(currReceiptDTO,3);

        Double finalPrice = contr.endSale();
        System.out.println("The final price is (including VAT): " + finalPrice + " SEK" + "\n");
        double payment = 200;
        System.out.println("Customer pays: " + payment + " SEK");
        ReceiptDTO finaReceiptDTO = contr.payment(payment);

        contr.printReceipt(finaReceiptDTO);
    }

    /**
     *  Information about a the last item in the list is printed to the console.
     * @param receiptDTO The receiptdto containing the information needed to print back to the console.
     * @param itemIdentifier The identifier of the item that is to be entered.
     */
    void printNewItem(ReceiptDTO receiptDTO, int itemIdentifier) {
        //DecimalFormat df = new DecimalFormat("#.##"); will fix this to make output look nicer if I have time.
        ItemDTO item = receiptDTO.getCurrentItemList().get(receiptDTO.getCurrentItemList().size()-1);
        System.out.println("Add item with item itemidentifier: "+ itemIdentifier);
        System.out.println("Item name: " + item.getItemName());
        System.out.println("Item price: " + item.getPrice() + " SEK");
        System.out.println("Item VAT: " + item.getTaxVAT()*100 + "%");
        System.out.println("Item description: " + item.getItemDescription() + "\n");
        System.out.println("Total price (including VAT): " + receiptDTO.getTotalPrice() + " SEK");
        System.out.println("Total VAT:   " + receiptDTO.getTotalVAT()+ "  SEK" + "\n");
    }
}
