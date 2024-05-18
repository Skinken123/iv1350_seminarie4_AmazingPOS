package se.kth.iv1350.amazingpos.main.view;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ResourceBundle.Control;

import se.kth.iv1350.amazingpos.main.controller.Controller;
import se.kth.iv1350.amazingpos.main.controller.GenericIssueException;
import se.kth.iv1350.amazingpos.main.integration.ItemIdentifierDoesNotExistException;
import se.kth.iv1350.amazingpos.main.model.dto.ItemDTO;
import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;
import se.kth.iv1350.amazingpos.main.util.FileLogger;

/**
 * Represents the view of the program. This class represents the user interface which we will not code in this project.
 * Instead, we will simulate a user interface by calling the controller with predefined calls. And print the results to the console.
 */

public class View {
    private Controller contr;
    private ErrorMessageFormatter errorMessages = new ErrorMessageFormatter();
    private FileLogger logger;

    /**
     * Creates a new instance of the view.
     * 
     * @param contr The controller that is used for all calls from the view.
     * @throws IOException 
     */
    public View(Controller contr) throws IOException {
        this.contr = contr;
        this.logger = new FileLogger();
    }

    /**
     * Simulates a user input that generates calls to all system operations.
     */
    public void sampleExecution() {
        try{
            contr.startSale();
            System.out.println("A new sale has been started!" + "\n");

            ReceiptDTO currReceiptDTO = contr.enterNewItem(1);
            printNewItem(currReceiptDTO,1);
            currReceiptDTO = contr.enterNewItem(2);
            printNewItem(currReceiptDTO,2);

            try{
                System.out.println("Sending an itemidentifier which should trigger a database faliure.");
                currReceiptDTO = contr.enterNewItem(10);
                printNewItem(currReceiptDTO,10);
                errorMessages.createFormattedErrorMessage("Created an item even though the database should not be working, exception failed.");
            }
            catch(GenericIssueException exc){
                errorMessages.createFormattedErrorMessage("Could not register item");
            }
            catch(ItemIdentifierDoesNotExistException exc){
                errorMessages.createFormattedErrorMessage("Could not register item");
                logger.logException(exc);
            }

            try{
                System.out.println("Sending an itemidentifier which does not exist in the inventory system and should trigger a exception.");
                currReceiptDTO = contr.enterNewItem(100);
                printNewItem(currReceiptDTO,100);
                errorMessages.createFormattedErrorMessage("Created an item that does not exist in the database, exception failed.");
            }
            catch(ItemIdentifierDoesNotExistException exc){
                errorMessages.createFormattedErrorMessage("Could not register item with itemidentifier " + exc.getItemIdentifier() + " because it does not exist in the database.");
                logger.logException(exc);
            }
            catch(GenericIssueException exc){
                errorMessages.createFormattedErrorMessage("Could not register item");
            }

            Double finalPrice = contr.endSale();
            System.out.println("The final price is (including VAT): " + finalPrice + " SEK" + "\n");
            double payment = 200;
            System.out.println("Customer pays: " + payment + " SEK");
            ReceiptDTO finaReceiptDTO = contr.payment(payment);

            contr.printReceipt(finaReceiptDTO);
        }
        catch(ItemIdentifierDoesNotExistException exc){
            errorMessages.createFormattedErrorMessage("Could not register item with itemidentifier " + exc.getItemIdentifier() + " because it does not exist in the database.");
            logger.logException(exc);
        }
        catch(Exception exc){
            errorMessages.createFormattedErrorMessage("An error has occured. Please try again.");
            logger.logException(exc);
        }
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
