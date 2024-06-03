package se.kth.iv1350.amazingpos.test.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import se.kth.iv1350.amazingpos.main.controller.Controller;
import se.kth.iv1350.amazingpos.main.integration.ExternalSystemsCreator;
import se.kth.iv1350.amazingpos.main.integration.printing.ReceiptPrinter;
import se.kth.iv1350.amazingpos.main.model.dto.ItemDTO;
import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;
import se.kth.iv1350.amazingpos.main.view.View;

/**
 * Tests the System.out output of the View class.
 */
public class ViewTest {
    private Controller controller;
    private View view;

    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;

     /**
     * Sets up the test class before each test method is run.
     */
    @BeforeEach
    public void setUpStreams() throws IOException {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        controller = new Controller(new ExternalSystemsCreator(),new ReceiptPrinter());
        view = new View(controller);
    }

     /**
     * Cleans up the test class after each test method is run.
     */
    @AfterEach
    public void cleanUpStreams() {
        outContent = null;
        System.setOut(originalSysOut);
    }

    /**
     * Tests the sampleExecution method in the View class and makes sure the correct printout is made.
     */
    @Test
    public void testSampleExcecution(){
        view.sampleExecution();
        String result = outContent.toString();

        assertTrue(result.contains("A new sale has been started!"), "Wrong printout.");
        assertTrue(result.contains("Add item with item itemidentifier: 1"), "Wrong printout.");
        assertTrue(result.contains("Item name: Tomato"), "Wrong printout.");
        assertTrue(result.contains("Item price: 35.0 SEK"), "Wrong printout.");
        assertTrue(result.contains("Item VAT: 12.0%"), "Wrong printout.");
        assertTrue(result.contains("Item description: A box of red tomatos"), "Wrong printout.");
        assertTrue(result.contains("Total price (including VAT): 35.0 SEK"), "Wrong printout.");
        assertTrue(result.contains("Total VAT:   4.2  SEK"), "Wrong printout.");
        assertTrue(result.contains("Sending an itemidentifier which should trigger a database faliure."), "Wrong printout.");
        assertTrue(result.contains("REPORTED ERROR: Could not register item"), "Wrong printout.");
        assertTrue(result.contains("Time and date of ERROR:"), "Wrong printout.");
        assertTrue(result.contains("Sending an itemidentifier which does not exist in the inventory system and should trigger a exception."), "Wrong printout.");
        assertTrue(result.contains("REPORTED ERROR: Could not register item with itemidentifier 100 because it does not exist in the database."), "Wrong printout.");
        assertTrue(result.contains("Time and date of ERROR:"), "Wrong printout.");
        assertTrue(result.contains("The sale has ended:"), "Wrong printout.");
        assertTrue(result.contains("The final price is (including VAT): 35.0 SEK"), "Wrong printout.");
        assertTrue(result.contains("Customer pays: 200.0 SEK"), "Wrong printout.");
        assertTrue(result.contains("Total revenue of all sales since program started: 35.0 SEK"), "Wrong printout.");
        assertTrue(result.contains("The total price of the most recent sale was: 35.0 SEK"), "Wrong printout.");
        assertTrue(result.contains("Accounting system updated."), "Wrong printout.");
        assertTrue(result.contains("The inventory has been updated"), "Wrong printout.");
        assertTrue(result.contains("Kvitto:"), "Wrong printout.");
        assertTrue(result.contains("Tid vid köp:"), "Wrong printout.");
        assertTrue(result.contains("Tomato"), "Wrong printout.");
        assertTrue(result.contains("Totalt pris (inkluderande moms): 35.0 SEK"), "Wrong printout.");
        assertTrue(result.contains("Total moms: 4.2 SEK"), "Wrong printout.");
        assertTrue(result.contains("Betalning: 200.0 SEK"), "Wrong printout.");
        assertTrue(result.contains("Växel: 165.0 SEK"), "Wrong printout.");
    }
}
