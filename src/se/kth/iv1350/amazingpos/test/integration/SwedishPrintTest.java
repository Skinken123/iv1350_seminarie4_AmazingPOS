package se.kth.iv1350.amazingpos.test.integration;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.amazingpos.main.integration.printing.ReceiptPrinter;
import se.kth.iv1350.amazingpos.main.model.dto.ItemDTO;
import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Tests the EnglishPrint class gives the correct output.
 */
public class SwedishPrintTest {
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;
    ReceiptPrinter testPrinter;

    /**
     * Sets up the test class before each test method is run.
     */
    @BeforeEach
    public void setUpStreams() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        testPrinter = new ReceiptPrinter();
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
     * Tests the print method in the EnglishPrint class.
     */
    @Test
    public void testPrint(){
        List<ItemDTO> items = new ArrayList<>();
        items.add(new ItemDTO(1.0, 1, "Tomato", "A box of red tomatos", 0.12, 1));
        ReceiptDTO testReceipt = new ReceiptDTO("Test", 0, 0, 0, 0, items);
        testPrinter.printReceipt(testReceipt);
        String result = outContent.toString();

        assertTrue(result.contains("Kvitto:"), "Wrong printout.");
        assertTrue(result.contains("Tid vid köp: Test"), "Wrong printout.");
        assertTrue(result.contains("Tomato"), "Wrong printout.");
        assertTrue(result.contains("Totalt pris (inkluderande moms): 0.0 SEK"), "Wrong printout.");
        assertTrue(result.contains("Total moms: 0.0 SEK"), "Wrong printout.");
        assertTrue(result.contains("Betalning: 0.0 SEK"), "Wrong printout.");
        assertTrue(result.contains("Växel: 0.0 SEK"), "Wrong printout.");
    }
}
