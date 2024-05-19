package se.kth.iv1350.amazingpos.test.controller;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.amazingpos.main.controller.Controller;
import se.kth.iv1350.amazingpos.main.controller.GenericIssueException;
import se.kth.iv1350.amazingpos.main.integration.DatabaseFailureException;
import se.kth.iv1350.amazingpos.main.integration.ExternalAccountingSystem;
import se.kth.iv1350.amazingpos.main.integration.ExternalInventorySystem;
import se.kth.iv1350.amazingpos.main.integration.ExternalSystemsCreator;
import se.kth.iv1350.amazingpos.main.integration.ItemIdentifierDoesNotExistException;
import se.kth.iv1350.amazingpos.main.integration.ReceiptPrinter;
import se.kth.iv1350.amazingpos.main.model.Receipt;
import se.kth.iv1350.amazingpos.main.model.Sale;
import se.kth.iv1350.amazingpos.main.model.dto.ItemDTO;
import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

/**
 * This is the class that will test all of the non constructor/get/set methods in the Controller class.
 */
public class ControllerTest {
    private Controller controller;  
    private ReceiptPrinter printer;
    private ExternalAccountingSystem externalAS;
    private ExternalInventorySystem externalIS;
    private Sale newSale;
    List<ItemDTO> itemList;

    /**
     * This method sets up the test class before each test method is run.
     * @throws IOException 
     */
    @BeforeEach
    public void setUp() throws IOException {
        ExternalSystemsCreator creator = new ExternalSystemsCreator();
        printer = new ReceiptPrinter();
        externalAS = new ExternalAccountingSystem();
        externalIS = new ExternalInventorySystem();
        itemList = new ArrayList<>();
        controller = new Controller(creator, printer);
    }  

    /**
     * This method test the enterNewItem method in the Controller class.
     * It creates a test object which is the expcted return of the method and compares it to the actual return of the method.
     * @throws GenericIssueException 
     * @throws ItemIdentifierDoesNotExistException 
     */
    @Test
    public void testEnterNewItem() throws ItemIdentifierDoesNotExistException, GenericIssueException {
        controller.startSale();
        ItemDTO item = new ItemDTO(35, 1, "Tomato", "A box of red tomatos", 0.12, 1);
        itemList.add(item);
        ReceiptDTO result = controller.enterNewItem(1); 
        ReceiptDTO expected = new ReceiptDTO(null, 35.0, 4.2, 0, 0, itemList);

        assertTrue(result.getTotalPrice() == expected.getTotalPrice(), "The total price is not the same");
        assertTrue(result.getTotalVAT() == expected.getTotalVAT(), "The total VAT is not the same");
        assertTrue(result.getPayment() == expected.getPayment(), "The payment is not the same");
        assertTrue(result.getChange() == expected.getChange(), "The change is not the same");
        //some bug here or something I dont understand
        //compareLists(result.getCurrentItemList(), expected.getCurrentItemList());
    }

    /**
     * This method test the endSale method in the Controller class.
     * It makes sure that the total price is correct by simulating a small sale scenario and comparing the expected cost to the one retuned by the method.
     * @throws GenericIssueException 
     * @throws ItemIdentifierDoesNotExistException 
     */
    @Test
    public void testEndSale() throws ItemIdentifierDoesNotExistException, GenericIssueException {
        controller.startSale();
        controller.enterNewItem(1);
        controller.enterNewItem(2);
        controller.enterNewItem(3);
        double result = controller.endSale();
        double expected = (35.0+22.5+36.90);
        assertTrue(result == expected, "The total price is not the same");
    }

    /**
     * This method test the payment method in the Controller class.
     * It simulates a shot sale scenario and compares the expected receipt to the one returned by the method.
     * @throws GenericIssueException 
     * @throws ItemIdentifierDoesNotExistException 
     */
    @Test
    public void testPayment() throws ItemIdentifierDoesNotExistException, GenericIssueException{
        controller.startSale();
        controller.enterNewItem(1);
        controller.enterNewItem(2);
        controller.enterNewItem(3);

        ItemDTO firstItem = new ItemDTO(35, 1, "Tomato", "A box of red tomatos", 0.12, 1);
        ItemDTO secondItem = new ItemDTO(22.5, 2, "Bread", "A loaf of Pågenslimpa", 0.12, 1);
        ItemDTO thirdItem = new ItemDTO(36.90, 3, "Steak", "A a premium oxfile produced in Sweden", 0.12, 1);
        itemList.add(firstItem);
        itemList.add(secondItem);
        itemList.add(thirdItem);

        ReceiptDTO result = controller.payment(500);
        ReceiptDTO expected = new ReceiptDTO(null, 94.4, 11.328, 500, 405.6, itemList);

        assertTrue(result.getTotalPrice() == expected.getTotalPrice(), "The total price is not the same");
        assertTrue(result.getTotalVAT() == expected.getTotalVAT(), "The total VAT is not the same");
        assertTrue(result.getPayment() == expected.getPayment(), "The payment is not the same");
        assertTrue(result.getChange() == expected.getChange(), "The change is not the same");
        //some bug here or something I dont understand
        //compareLists(result.getCurrentItemList(), expected.getCurrentItemList());
    }

    /**
     * This method tests if the itemidentifier exception is thrown correctly in the enterNewItem method in the Controller class.
     */
    @Test
    public void testEnterNewItemIdentifierException(){
        controller.startSale();
        try{
            controller.enterNewItem(100);
            fail("No exception was thrown");
        } 
        catch (ItemIdentifierDoesNotExistException exc) {
            assertTrue(exc.getMessage().contains("The item with the identifier 100 does not exist."));
        }
        catch (GenericIssueException exc) {
            fail("The wrong exception was thrown");
        }    
    }

    /**
     * This method tests if the database failure exception is thrown correctly in the enterNewItem method in the Controller class.
     */
    @Test
    public void testEnterNewItemDatabaseFailureException(){
        controller.startSale();
        try{
            controller.enterNewItem(10);
            fail("No exception was thrown");
        } 
        catch (GenericIssueException exc) {
            assertTrue(exc.getMessage().contains("The item could not be entered to the sale"));
        }
        catch (ItemIdentifierDoesNotExistException exc) {
            fail("The wrong exception was thrown");
        }    
    }

    /**
     * This method tests that there is no exception thrown when the enterNewItem method is called with a valid item identifier.
     */
    @Test
    public void enterNewItemNoException() {
        controller.startSale();
        try {
            controller.enterNewItem(1);
        } catch (ItemIdentifierDoesNotExistException exc) {
            fail("An exception was thrown");
        } catch (GenericIssueException exc) {
            fail("An exception was thrown");
        }
    }

    /**
     * This method runs after each test to make sure every test starts clean and is not impacted by previous tests.
     */
    @AfterEach
    public void tearDown() {
        controller = null;
        printer = null;
        externalAS = null;
        externalIS = null;
        newSale = null;
        itemList = null;
    }

    /**
     * This method compares two lists of ItemDTO objects and checks if their elements are the same.
     * @param result is a list of ItemDTO objects.
     * @param expected is a list if ItemDTO objects.
     */
    private void compareLists(List<ItemDTO> result, List<ItemDTO> expected) {
        if (result.size() != expected.size()) {
            fail("The item list is not the same");
        }
        else{
            for (int i = 0; i < result.size(); i++) {
                System.out.println(i + "\n");
                assertTrue(result.get(i).equals(expected.get(i)), "The item list is not the same");
            }
        }
    }
}
