package se.kth.iv1350.amazingpos.main.integration;

import se.kth.iv1350.amazingpos.main.model.dto.ItemDTO;
import se.kth.iv1350.amazingpos.main.model.dto.ItemListDTO;

/**
 * Represents a class which will communicate with the actual inventory system.
 */

public class ExternalInventorySystem {
    /**
     * takes an itemIdentifier and returns the item data for that item. 
     * There is no implementation regarding if the itemIdentifier is invalid since this is not requierd in this seminar.
     * We will hence asume the item identifier is always valid and return an item. Since we do not have a database we will just return a hardcoded item to test the program. 
     * In order to test diffrent scenarios the program we will return different items depending on the itemIdentifier.
     * 
     * @param itemIdentifier The identifier of the item scanned by the cashier.
     * @throws ItemIdentifierDoesNotExistException 
     * @throws DatabaseFailureException 
     */
    public ItemDTO requestItemData(int itemIdentifier) throws ItemIdentifierDoesNotExistException, DatabaseFailureException {
        //some code that fetches the item data from the inventory system

        if (itemIdentifier == 10){
            throw new DatabaseFailureException("System failure, could not fetch item data from the inventory system.");
        }
        else if (itemIdentifier < 1 || itemIdentifier > 4) {
            throw new ItemIdentifierDoesNotExistException(itemIdentifier);
        }
        
        if (itemIdentifier == 1) {
            return new ItemDTO(35, itemIdentifier, "Tomato", "A box of red tomatos", 0.12, 1);
        } else if (itemIdentifier == 2) {
            return new ItemDTO(22.5, itemIdentifier, "Bread", "A loaf of Pågenslimpa", 0.12, 1);
        } else if (itemIdentifier == 3) {
            return new ItemDTO(36.90, itemIdentifier, "Steak", "A a premium oxfile produced in Sweden", 0.12, 1);
        } else if (itemIdentifier == 4) {
            return new ItemDTO(75.90, itemIdentifier, "Cheese", "A block of Greve cheese", 0.12, 1);
        }
        return null;
    }

    /**
     * Sends the information of the sold items to the external inventory system to update the inventory.
     * We simulate the update of the inventory by printing a message to the console.
     * 
     * @param itemListDTO The itemListDTO object that contains a list of all the items that were sold.
     */
    public void updateInventory(ItemListDTO itemListDTO) {
        //some code that updates the inventory system
        System.out.println("The inventory has been updated" + "\n");
    }
        
}
