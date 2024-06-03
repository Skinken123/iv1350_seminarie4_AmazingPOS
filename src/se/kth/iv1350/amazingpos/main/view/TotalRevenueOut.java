package se.kth.iv1350.amazingpos.main.view;

import se.kth.iv1350.amazingpos.main.model.SaleObserver;

/**
 * A class that displays and logs the total revenue of all sales since the program started.
 */
public abstract class TotalRevenueOut implements SaleObserver{
    private double totalRevenue = 0;
    private ErrorMessageFormatter errorMessages = new ErrorMessageFormatter();

    protected TotalRevenueOut() {
    }

    /**
     * Updates the total revenue with the most recent sale and prints the total revenue of all sales since the program started as well as logging it to a file.
     * 
     * @param totalPriceFromMostRecentSale The total price of the most recent sale.
     */
    @Override
    public void updateSaleProfits(double totalPriceFromMostRecentSale){
        calculateTotalIncome(totalPriceFromMostRecentSale);
        showTotalIncome(totalPriceFromMostRecentSale);
    }

    /**
     * Calculates the total income of all sales since the program started.
     * 
     * @param totalPriceFromMostRecentSale The total price of the most recent sale.
     * @return The total income of all sales since the program started.
     */
    private double calculateTotalIncome(double totalPriceFromMostRecentSale) {
        return totalRevenue += totalPriceFromMostRecentSale;
    }

    /**
     * Shows the total income of all sales since the program started. Both in the console and in a log file.
     * 
     * @param totalPriceFromMostRecentSale The total price of the most recent sale.
     */
    private void showTotalIncome(double totalPriceFromMostRecentSale){
        try{
            doShowTotalIncome(totalRevenue, totalPriceFromMostRecentSale);
        } catch (Exception e){
            handelErrors(e, errorMessages);
        }
    }

    /**
     * Shows the total income of all sales since the program started.
     * 
     * @param totalRev The total revenue of all sales since the program started.
     * @param totalPriceFromMostRecentSale The total price of the most recent sale.
     * @throws Exception is thrown if the total revenue could not be shown.
     */
    protected abstract void doShowTotalIncome(double totalRev, double totalPriceFromMostRecentSale) throws Exception;

    /**
     * Handles errors that occur when trying to show the total revenue of all sales since the program started and displays a message to the user.
     * 
     * @param e The exception that casued the error.
     * @param errorMessages The error message formatter.
     */
    protected abstract void handelErrors(Exception e, ErrorMessageFormatter errorMessages);
}
