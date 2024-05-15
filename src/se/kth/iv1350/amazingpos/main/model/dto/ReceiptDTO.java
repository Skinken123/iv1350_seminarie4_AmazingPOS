package se.kth.iv1350.amazingpos.main.model.dto;

import java.time.LocalTime;
import java.util.List;

/**
 * Represents a data transfer object (dto) for the receipt class,
 * used for sending information about an instance of the receipt class though packages.
 */
public class ReceiptDTO {
    private LocalTime saleTime;
    private double totalPrice;
    private double totalVAT;
    private double payment;
    private double change;
    private List<ItemDTO> currentItemList;

    /**
     * Creates a new instance of a receiptDTO.
     * 
     * @param saleTime The time of the sale.
     * @param totalPrice The total price of the sale.
     * @param totalVAT The total VAT of the sale.
     * @param payment The payment of the sale.
     * @param change The change of the sale.
     * @param basicItemList The list of items in the sale.
     */
    public ReceiptDTO(LocalTime saleTime, double totalPrice, double totalVAT, double payment, double change, List<ItemDTO> currentItemList) {
        this.saleTime = saleTime;
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.payment = payment;
        this.change = change;
        this.currentItemList = currentItemList;
    }

    /**
     * Gets the time of the sale.
     * 
     * @return The time of the sale.
     */
    public LocalTime getSaleTime() {
        return saleTime;
    }

    /**
     * Gets the total price of the sale.
     * 
     * @return The total price of the sale.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Gets the total VAT of the sale.
     * 
     * @return The total VAT of the sale.
     */
    public double getTotalVAT() {
        return totalVAT;
    }

    /**
     * Gets the payment of the sale.
     * 
     * @return The payment of the sale.
     */
    public double getPayment() {
        return payment;
    }

    /**
     * Gets the change of the sale.
     * 
     * @return The change of the sale.
     */
    public double getChange() {
        return change;
    }

    /**
     * Gets the list of items in the sale.
     * 
     * @return The list of items in the sale.
     */
    public List<ItemDTO> getCurrentItemList() {
        return currentItemList;
    }
}
