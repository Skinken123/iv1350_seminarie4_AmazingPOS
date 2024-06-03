package se.kth.iv1350.amazingpos.test.integration;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.amazingpos.main.integration.ExternalAccountingSystem;
import se.kth.iv1350.amazingpos.main.integration.printing.ReceiptPrinter;
import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;

public class ExternalAccountingSystemTest {
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;
    ExternalAccountingSystem externalAccountingSystem;
     /**
     * Sets up the test class before each test method is run.
     */
    @BeforeEach
    public void setUpStreams() {
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        externalAccountingSystem = new ExternalAccountingSystem();
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
     * Tests that the updateAccountingSystem gives the correct output.
     */
    @Test
    public void testUpdateAccountingSystem(){
        ReceiptDTO testReceipt = new ReceiptDTO(null, 0, 0, 0, 0, null);
        externalAccountingSystem.updateAccountingSystem(testReceipt);
        String result = outContent.toString();

        assertTrue(result.contains("Accounting system updated."));
    }
}
