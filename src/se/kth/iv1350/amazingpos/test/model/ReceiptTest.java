package se.kth.iv1350.amazingpos.test.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.amazingpos.main.model.Receipt;
import se.kth.iv1350.amazingpos.main.model.dto.ItemDTO;
import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;


/**
 * This is the class that will test all of the non constructor/get/set methods in the Receipt class.
 */
public class ReceiptTest {
    private Receipt receipt;
    private List<ItemDTO> itemList;

    /**
     * This method sets up the test class before each test method is run.
     */
    @BeforeEach
    public void setUp() {
        receipt = new Receipt();
        itemList = new ArrayList<>();
        receipt.setSaleTime(LocalTime.now());
        receipt.setTotalPrice(0);
        receipt.setTotalVAT(0);
        receipt.setPayment(0);
        receipt.setChange(0);
        receipt.setCurrentItemList(itemList);
    }

    /**
     * This method tests the updateVATPriceList method in the Receipt class.
     * It creates a test values of the expcted return of the method and compares it to the actual return of the method.
     * It comfirms if the return is correct by each comparing each test value to the return value..
     */

    @Test
    public void testUpdateVATPriceList(){
        ItemDTO item = new ItemDTO(10, 1, "Milk", "1L", 0.12, 1);
        List<ItemDTO> newItemList = new ArrayList<>();
        newItemList.add(item);
        receipt.updateVATPriceList(newItemList);

        assertEquals(10.0, receipt.getTotalPrice(), "The total price is not the same");
        assertEquals(1.2, receipt.getTotalVAT(), "The total VAT is not the same");
        assertEquals(newItemList, receipt.getCurrentItemList(), "The item list is not the same");
    }

    /**
     * This method tests the setSaleTimeAndPayment method in the Receipt class.
     * It runs the method which we want to test and compares the return value to the expected values which are the inputs.
     * It comfirms if the return is correct by comparing each of the attributes affected by the method.
     */
    @Test
    public void testSetSaleTimeAndPayment(){
        ReceiptDTO result = receipt.setSaleTimeAndPayment(300, 100);

        assertEquals(300,result.getPayment(), "The payment is not the same");
        assertEquals(100,result.getChange(), "The change is not the same");
    }

    /**
     * This method resets the test class after each test method is run.
    */
    @AfterEach
    public void tearDown() {
        receipt = null;
        itemList = null;
    }
}
