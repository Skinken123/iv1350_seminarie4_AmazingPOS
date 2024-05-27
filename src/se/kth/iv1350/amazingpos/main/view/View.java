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
import se.kth.iv1350.amazingpos.main.utility.FileLogger;

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
     * @throws IOException is thrown if the file logger cannot be created.
     */
    public View(Controller contr) throws IOException {
        this.contr = contr;
        contr.addSaleObserver(new TotalRevenueView());
        contr.addSaleObserver(new TotalRevenueFileOutput());
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
                String result = errorMessages.createFormattedErrorMessage("Created an item even though the database should not be working, exception failed.");
                System.out.println(result);
            }
            catch(GenericIssueException exc){
                String result = errorMessages.createFormattedErrorMessage("Could not register item");
                System.out.println(result);
            }
            catch(ItemIdentifierDoesNotExistException exc){
                String result = errorMessages.createFormattedErrorMessage("Could not register item");
                System.out.println(result);
                logger.logExceptionToFile(exc);
            }

            try{
                System.out.println("Sending an itemidentifier which does not exist in the inventory system and should trigger a exception.");
                currReceiptDTO = contr.enterNewItem(100);
                printNewItem(currReceiptDTO,100);
                String result = errorMessages.createFormattedErrorMessage("Created an item that does not exist in the database, exception failed.");
                System.out.println(result);
            }
            catch(ItemIdentifierDoesNotExistException exc){
                String result = errorMessages.createFormattedErrorMessage("Could not register item with itemidentifier " + exc.getItemIdentifier() + " because it does not exist in the database.");
                System.out.println(result);
                logger.logExceptionToFile(exc);
            }
            catch(GenericIssueException exc){
                String result = errorMessages.createFormattedErrorMessage("Could not register item");
                System.out.println(result);
            }

            Double finalPrice = contr.endSale();
            System.out.println("The final price is (including VAT): " + finalPrice + " SEK" + "\n");
            double payment = 200;
            System.out.println("Customer pays: " + payment + " SEK\n");
            ReceiptDTO finaReceiptDTO = contr.payment(payment);

            contr.printReceipt(finaReceiptDTO);
            System.out.println("\n");
            //Second test run just for testing the observer pattern.
            contr.startSale();
            contr.enterNewItem(1);
            contr.enterNewItem(2);
            contr.endSale();
            contr.payment(200);
        }
        catch(ItemIdentifierDoesNotExistException exc){
            String result = errorMessages.createFormattedErrorMessage("Could not register item with itemidentifier " + exc.getItemIdentifier() + " because it does not exist in the database.");
            System.out.println(result);
            logger.logExceptionToFile(exc);
        }
        catch(Exception exc){
            String result = errorMessages.createFormattedErrorMessage("An error has occured. Please try again.");
            System.out.println(result);
            logger.logExceptionToFile(exc);
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
