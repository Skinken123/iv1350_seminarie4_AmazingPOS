package se.kth.iv1350.amazingpos.main.view;

import se.kth.iv1350.amazingpos.main.model.SaleObserver;

/**
 * A class that displays the total revenue of all sales since the program started.
 */
public class TotalRevenueView implements SaleObserver{
    private double totalRevenue = 0;

    /**
     * Updates the total revenue with the most recent sale and prints the total revenue of all sales since the program started.
     * 
     * @param totalPriceFromMostRecentSale The total price of the most recent sale.
     */
    @Override
    public void updateSaleProfits(double totalPriceFromMostRecentSale){
        totalRevenue += totalPriceFromMostRecentSale;
        System.out.println("Total revenue of all sales since program started: " + totalRevenue + " SEK\n");
    }
    
}
