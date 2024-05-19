package se.kth.iv1350.amazingpos.main.model;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.amazingpos.main.model.dto.ItemDTO;
import se.kth.iv1350.amazingpos.main.model.dto.ReceiptDTO;

/**
 * Represents a sale, the primary job of this class is to store the items being bought in the sale.
 */
public class Sale {
    private Receipt newReceipt;
    private List<ItemDTO> itemList = new ArrayList<>();
    private List<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Creates a new instance of a sale and creates a instance of the receipt when a new sale is started.
     */
    public Sale() {
        newReceipt = new Receipt();
    }

    /**
     * Gets the list of items in the sale.
     * 
     * @return The list of items in the sale.
     */

    public List<ItemDTO> getItemList() {
        return this.itemList;
    }

    /**
     * Adds an item to the sale. If the item already exists in the sale, the quantity of the item will be increased.
     * 
     * @param item The item to be added to the sale.
     * @return The receiptDTO with the updated item list.
     */
    public ReceiptDTO uppdateItemList(ItemDTO newItem) {
        boolean itemExists = false;
        int itemNumber = 0;
        int index = 0;
        ItemDTO updatedItem = null;
        for (ItemDTO item : itemList) {
            if (item.getItemName().equals(newItem.getItemName())) {
                updatedItem = new ItemDTO(item.getPrice(), item.getItemIdentifier(), item.getItemName(), item.getItemDescription(), item.getTaxVAT(), item.getQuantity() + newItem.getQuantity());
                itemExists = true;
                index = itemNumber;
                break;
            }
            else {
                itemNumber++;
            }
        }
        if (!itemExists) {
            itemList.add(newItem);
        }
        else {
            itemList.remove(index);
            itemList.add(updatedItem);
        }
        ReceiptDTO currenReceiptDTO = newReceipt.updateVATPriceList(itemList);
        return currenReceiptDTO;
    }

    /**
     * Gets the total price of the sale. 
     * 
     * @return The total price of the sale.
     */
    public double getTotalPrice() {
        return newReceipt.getTotalPrice();
    }

    /**
     * Gets the final receiptDTO with the final sale information. This method is called when the customer has payed for the sale and the sale is finished.
     * 
     * @param payment The payment made by the customer.
     * @param change The change that will be given to the customer.
     * @return The final receiptDTO with the final sale information.
     */

    public ReceiptDTO getFinalReceiptDTO(double payment, double change) {
        ReceiptDTO finalReceiptDTO = newReceipt.setSaleTimeAndPayment(payment, change);
        double totalPrice = getTotalPrice();
        notifyObservers(totalPrice);
        return finalReceiptDTO;
    }

    /**
     * Notifies all sale observers that a sale has been concluded and the total sale profits need to be updated.
     */
    private void notifyObservers(double price) {
        for (SaleObserver saleObserver : saleObservers) {
            saleObserver.updateSaleProfits(price);
        }
    }

    /**
     * Adds a list of sale observers to the list of sale observers.
     * 
     * @param saleObservers The list of sale observers to be added to the list of sale observers.
     */
    public void addSaleObservers(List<SaleObserver> saleObserversFromController) {
        saleObservers.addAll(saleObserversFromController);
    }
}
