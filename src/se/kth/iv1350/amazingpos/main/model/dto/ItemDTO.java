package se.kth.iv1350.amazingpos.main.model.dto;

/**
 * Represents an item being bought in the sale, the primary job of this class is to store the information of an item.
 * It is a dto since the values of the object will be static and not change during the program.
 * Additionally, the object will be sent to other classes and sending an object is easier than sending multiple primitve data.
 */
public class ItemDTO {
    private double price;
    private int itemIdentifier;
    private String itemName;
    private String itemDescription;
    private double taxVAT;
    private int quantity;

    /**
     * Creates a new instance of an itemDTO.
     * 
     * @param price The price of the item.
     * @param itemIdentifier The identifier of the item.
     * @param itemName The name of the item.
     * @param itemDescription The description of the item.
     * @param taxVAT The VAT of the item.
     * @param quantity The quantity of the item.
     */
    public ItemDTO(double price, int itemIdentifier, String itemName, String itemDescription, double taxVAT, int quantity) {
        this.price = price;
        this.itemIdentifier = itemIdentifier;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.taxVAT = taxVAT;
        this.quantity = quantity;
    }

    /**
     * Gets the price of the item.
     * 
     * @return The price of the item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the identifier of the item.
     * 
     * @return The identifier of the item.
     */
    public int getItemIdentifier() {
        return itemIdentifier;
    }

    /**
     * Gets the name of the item.
     * 
     * @return The name of the item.
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Gets the description of the item.
     * 
     * @return The description of the item.
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Gets the VAT of the item.
     * 
     * @return The VAT of the item.
     */
    public double getTaxVAT() {
        return taxVAT;
    }

    /**
     * Gets the quantity of the item.
     * 
     * @return The quantity of the item.
     */
    public int getQuantity() {
        return quantity;
    }
}
