package se.kth.iv1350.amazingpos.main.view;

/**
 * A class that displays the total revenue of all sales since the program started.
 */
public class TotalRevenueView extends TotalRevenueOut{
    /**
     * Updates the total revenue with the most recent sale and prints the total revenue of all sales since the program started.
     * 
     * @param totalPriceFromMostRecentSale The total price of the most recent sale.
     */
    @Override
    protected void doShowTotalIncome(double totalRevenue, double totalPriceFromMostRecentSale){
        System.out.println("Total revenue of all sales since program started: " + totalRevenue + " SEK");
        System.out.println("The total price of the most recent sale was: " + totalPriceFromMostRecentSale + " SEK\n");
    }

    /**
     * Prints error message to the console if the total revenue could not be printed.
     * 
     * @param e The exception that caused the error.
     */
    @Override
    protected void handelErrors(Exception e, ErrorMessageFormatter errorMessages){
        System.out.println(errorMessages.createFormattedErrorMessage("Could not print total revenue to console."));
    }
}
