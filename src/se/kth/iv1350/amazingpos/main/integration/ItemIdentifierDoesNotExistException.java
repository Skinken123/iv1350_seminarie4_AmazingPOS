package se.kth.iv1350.amazingpos.main.integration;

/**
 * Thrown when an item identifier does not match any of the items in the database.
 */
public class ItemIdentifierDoesNotExistException extends Exception{
    private int itemIdentifier;

    /**
     * Creates a new instance specifying the item identifier that did not match any of the items in the database.
     * 
     * @param itemIdentifier The identifier of the item that does not exist.
     */
    public ItemIdentifierDoesNotExistException(int itemIdentifier){
        super("The item with the identifier " + itemIdentifier + " does not exist.");
        this.itemIdentifier = itemIdentifier;
    }

    /**
     * Get the item identifier that does not exist.
     * 
     * @return The item identifier that does not exist.
     */
    public int getItemIdentifier(){
        return itemIdentifier;
    }
}
