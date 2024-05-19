package se.kth.iv1350.amazingpos.main.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import se.kth.iv1350.amazingpos.main.integration.DatabaseFailureException;
import se.kth.iv1350.amazingpos.main.integration.ExternalAccountingSystem;
import se.kth.iv1350.amazingpos.main.integration.ExternalInventorySystem;
import se.kth.iv1350.amazingpos.main.integration.ExternalSystemsCreator;
import se.kth.iv1350.amazingpos.main.integration.ItemIdentifierDoesNotExistException;
import se.kth.iv1350.amazingpos.main.integration.ReceiptPrinter;
import se.kth.iv1350.amazingpos.main.model.Receipt;
import se.kth.iv1350.amazingpos.main.model.Sale;
import se.kth.iv1350.amazingpos.main.model.dto.ItemDTO;
import se.kth.iv1350.amazingpos.main.model.dto.ItemListDTO;
import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;
import se.kth.iv1350.amazingpos.main.utility.FileLogger;

/**
 * This is the application's only controller. All calls to the model pass through here.
 */
public class Controller {
    private ReceiptPrinter printer;
    private ExternalAccountingSystem externalAS;
    private ExternalInventorySystem externalIS;
    private Sale newSale;
    private FileLogger logger;

    /**
     * Creates a new instance of the controller.
     * 
     * @param creator The creator of the external systems.
     * @param printer The printer that will print the receipt.
     */
    public Controller(ExternalSystemsCreator creator, ReceiptPrinter printer) throws IOException {
        this.printer = printer;
        this.externalAS = creator.getExternalAS();
        this.externalIS = creator.getExternalIS();
        this.logger = new FileLogger();
    }

    /**
     * Starts a new sale. This method must be called before doing anything else during a sale.
     */

    public void startSale() {
        newSale = new Sale();
    }

    /**
     * Enters an item to the current sale. This method must be called after a sale has been started.
     * Step 1: The item identifier is sent to the external inventory system to get a item dto object back with the item information.
     * Step 2: The item dto object is sent to the sale object to be added to the current sale.
     * Step 3: The updated sale information is sent back to the view to be displayed to the cashier and customer.
     * 
     * @param itemIdentifier The identifier of the item that is to be entered.
     * @param quantity The quantity of the item that is to be entered.
     * @return The DTO of the current receipt information after the item has been entered. 
     * The dto will be used by the view to display the updated sale infromation to the cashier and customer.
     * @throws GenericIssueException 
     */
    public ReceiptDTO enterNewItem(int itemIdentifier) throws ItemIdentifierDoesNotExistException, GenericIssueException{
        try{
            ItemDTO newItem = externalIS.requestItemData(itemIdentifier);
            ReceiptDTO currenReceiptDTO = newSale.uppdateItemList(newItem);
            return currenReceiptDTO;
        }
        catch(DatabaseFailureException databaseExc)
        {
            logger.logExceptionToFile(databaseExc);
            throw new GenericIssueException("The item could not be entered to the sale", databaseExc);
        }
        
    }

    /**
     * Ends the current sale. This method must be called before payment.
     * 
     * @return The total price of the sale.
     */
    public double endSale() {
        System.out.println("The sale has ended:");
        return newSale.getTotalPrice();
    }

    /**
     * This method is called when the customer pays for the sale. The method will update the external systems with the final sale information.
     * The method will also return the final sale information in the form of a receiptDTO.
     * 
     * @param payment The payment made by the customer.
     * @return The final sale information in the form of a receiptDTO.
     */
    public ReceiptDTO payment(double amountPaid){
        double totalPrice = newSale.getTotalPrice();
        double change = amountPaid - totalPrice;
        ReceiptDTO finalReceiptDTO = newSale.getFinalReceiptDTO(amountPaid, change);
        externalAS.updateAccountingSystem(finalReceiptDTO);

        List<ItemDTO> finalItemList = newSale.getItemList();
        ItemListDTO finalItemListDTO = new ItemListDTO(finalItemList);
        externalIS.updateInventory(finalItemListDTO);
        return finalReceiptDTO;
    }

    /**
     * Calls the receipt printer to print the receipt.
     * 
     * @param receipt the object containing all information that the receipt printer needs to print the receipt.
     */
    public void printReceipt(ReceiptDTO receiptDTO) {
        printer.printReceipt(receiptDTO);
    }
}
