package se.kth.iv1350.amazingpos.test.model;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import se.kth.iv1350.amazingpos.main.model.Receipt;
import se.kth.iv1350.amazingpos.main.model.Sale;
import se.kth.iv1350.amazingpos.main.model.dto.ItemDTO;
import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

/**
 * This is the class that will test all of the non constructor/get/set methods in the Sale class.
 
 */

public class SaleTest {
    private Sale sale;
    private Receipt receipt;
    private List<ItemDTO> itemList;
    private LocalTime saleTime;
    private double payment;
    private double change;

    /**
     * This method sets up the test class before each test method is run.
     */

    @BeforeEach
    public void setUp() {
        receipt = new Receipt();
        itemList = new ArrayList<>();
        sale = new Sale();
        saleTime = null;
    }

    /**
     * This method tests the uppdateItemList method in the Sale class. 
     * It creates a test object which is the expcted return of the method and compares it to the actual return of the method.
     * It comfirms if the return is correct by each individual attribute of the two objects.
     */
    @Test
    public void testUppdateItemList() {
        ItemDTO item = new ItemDTO(10, 1, "Milk", "1L", 0.12, 1);
        itemList.add(item);
        ReceiptDTO result = sale.uppdateItemList(item);
        ReceiptDTO expected = new ReceiptDTO(createStringForTimeAndDate(), 10.0, 1.2, payment, change, itemList);
         
        double resPrice = result.getTotalPrice();
        double expPrice = expected.getTotalPrice();
        assertTrue(resPrice == expPrice, "The total price is not the same");

        double resVAT = result.getTotalVAT();
        double expVAT = expected.getTotalVAT();
        assertTrue(resVAT == expVAT, "The total VAT is not the same");

        double resPayment = result.getPayment();
        double expPayment = expected.getPayment();
        assertTrue(resPayment == expPayment, "The payment is not the same");

        double resChange = result.getChange();
        double expChange = expected.getChange();
        assertTrue(resChange == expChange, "The change is not the same");

        String resTime = result.getSaleTime();
        String expTime = expected.getSaleTime();
        assertTrue(resTime == expTime, "The sale time is not the same");

        List<ItemDTO> resItemList = result.getCurrentItemList();
        List<ItemDTO> expItemList = expected.getCurrentItemList();
        assertTrue(resItemList.equals(expItemList), "The item list is not the same");
        
    }

    /**
     * This method tests the getTotalPrice method in the Sale class.
     * It creates a test value which is the expcted return of the method and compares it to the actual return of the method.
     */

    @Test
    public void testGetTotalPrice() {
        ItemDTO item = new ItemDTO(10, 1, "Milk", "1L", 0.12, 1);
        itemList.add(item);
        sale.uppdateItemList(item);
        double result = sale.getTotalPrice();
        double expected = 10.0;
        assertEquals(expected, result, "The total price is not the same");
    }

    /**
     * This method tests the getFinalReceiptDTO method in the Sale class.
     * It creates a test object which is the expcted return of the method and compares it to the actual return of the method.
     * It comfirms if the return is correct by each individual attribute of the two objects.
     * I tried testing local time but it is very hard since the time is very precise and always changing.
     */

    @Test
    public void testGetFinalReceiptDTO(){
        ItemDTO item = new ItemDTO(10, 1, "Milk", "1L", 0.12, 1);
        itemList.add(item);
        sale.uppdateItemList(item);
        ReceiptDTO result = sale.getFinalReceiptDTO(100, 50);
        ReceiptDTO expected = new ReceiptDTO(createStringForTimeAndDate(), 10.0, 1.2, 100, 50, itemList);
        
        double resPrice = result.getTotalPrice();
        double expPrice = expected.getTotalPrice();
        assertTrue(resPrice == expPrice, "The total price is not the same");

        double resVAT = result.getTotalVAT();
        double expVAT = expected.getTotalVAT();
        assertTrue(resVAT == expVAT, "The total VAT is not the same");

        double resPayment = result.getPayment();
        double expPayment = expected.getPayment();
        assertTrue(resPayment == expPayment, "The payment is not the same");

        double resChange = result.getChange();
        double expChange = expected.getChange();
        assertTrue(resChange == expChange, "The change is not the same");

        List<ItemDTO> resItemList = result.getCurrentItemList();
        List<ItemDTO> expItemList = expected.getCurrentItemList();
        assertTrue(resItemList.equals(expItemList), "The item list is not the same");
    
    }

    /**
     * This method tears down the test class after each test method is run. 
     * Preparing it for a new test.
     */

    @AfterEach
    public void tearDown() {
        sale = null;
        receipt = null;
        itemList = null;
        saleTime = null;
    }

    /**
     * Creates a string with the current date and time by getting the current date and time and changing the format to be more clear and readable.
     * 
     * @return The current date and time as a string.
     */
    private String createStringForTimeAndDate() {
        LocalDateTime currentDateAndTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        String formattedDateTime = currentDateAndTime.format(formatter);
        return formattedDateTime;
    }
}
