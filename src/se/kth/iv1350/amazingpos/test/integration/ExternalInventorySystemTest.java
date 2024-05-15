package se.kth.iv1350.amazingpos.test.integration;

import se.kth.iv1350.amazingpos.main.integration.ExternalInventorySystem;
import se.kth.iv1350.amazingpos.main.model.dto.ItemDTO;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

/**
 * This class will test the relavant methods in the ExternalInventorySystem class.
 */

public class ExternalInventorySystemTest {
    private ExternalInventorySystem externalInventorySystem;

    /**
     * This method sets up the test class before each test method is run.
     */
    @BeforeEach
    public void setUp() {
        externalInventorySystem = new ExternalInventorySystem();
    }

    /**
     * This method tests the requestItemData method in the ExternalInventorySystem class.
     * It creates a test object which is the expcted return of the method and compares it to the actual return of the method.
     * It comfirms if the return is correct by each individual attribute of the two objects.
     */
    @Test
    public void testRequestItemDataCorrectItemIdentifier() {
        ItemDTO expected = new ItemDTO(35, 1, "Tomato", "A box of red tomatos", 0.12, 1);
        ItemDTO result = externalInventorySystem.requestItemData(1);

        assertTrue(expected.getPrice() == result.getPrice(), "The price is not the same");
        assertTrue(expected.getItemIdentifier() == result.getItemIdentifier(), "The itemIdentifier is not the same");
        assertTrue(expected.getItemName().equals(result.getItemName()), "The itemName is not the same");
        assertTrue(expected.getItemDescription().equals(result.getItemDescription()), "The itemDescription is not the same");
        assertTrue(expected.getTaxVAT() == result.getTaxVAT(), "The taxVAT is not the same");
    }

    /**
     * This method tests the requestItemData method in the ExternalInventorySystem class.
     * It creates a test object which is the not the expcted return of the method and compares it to the actual return of the method.
     * It comfirms that the return is not equal to the incorrect object created, by checking each individual attribute of the two objects.
     * We ignore testing the taxVAT and price since these attributes can be the same in multiple items, but the 3 other attribute are always unique.
     */
    @Test
    public void testRequestItemDataIncorrectItemIdentifier() {
        ItemDTO expected = new ItemDTO(35, 1, "Tomato", "A box of red tomatos", 0.12, 1);
        ItemDTO result = externalInventorySystem.requestItemData(3);

        assertFalse(expected.getItemIdentifier() == result.getItemIdentifier(), "The itemIdentifier is the same");
        assertFalse(expected.getItemName().equals(result.getItemName()), "The itemName is the same");
        assertFalse(expected.getItemDescription().equals(result.getItemDescription()), "The itemDescription is the same");
    }
    
    /**
     * This method resets the class after each test method is run.
     * This is done to prepare for the next test.
     */
    @AfterEach
    public void tearDown() {
        externalInventorySystem = null;
    }
    
}
