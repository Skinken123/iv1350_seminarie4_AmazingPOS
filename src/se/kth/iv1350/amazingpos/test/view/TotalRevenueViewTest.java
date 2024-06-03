package se.kth.iv1350.amazingpos.test.view;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.amazingpos.main.model.SaleObserver;
import se.kth.iv1350.amazingpos.main.view.TotalRevenueView;

/**
 * Tests the System.out output of the TotalRevenueView class.
 */
public class TotalRevenueViewTest {
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
     * Tests the doShowTotalIncome method in the TotalRevenueView class and makes sure the correct printout is made.
     */
    @Test
    public void testDoShowTotalIncome(){
        SaleObserver newObserver = new TotalRevenueView();
        newObserver.updateSaleProfits(100.0);
        String result = outContent.toString();
        
        assertTrue(result.contains("Total revenue of all sales since program started: 100.0 SEK"), "Wrong printout.");
        assertTrue(result.contains("The total price of the most recent sale was: 100.0 SEK"), "Wrong printout.");
    }
}
