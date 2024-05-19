package se.kth.iv1350.amazingpos.main.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import se.kth.iv1350.amazingpos.main.model.SaleObserver;

/**
 * This class is responsible for taking the total revenue from all sales since the program started and logging it to a file.
 */
public class TotalRevenueFileOutput implements SaleObserver{
    private static final String LOG_FILE_NAME = "amazingpos-saleprofitslog.txt";
    private PrintWriter fileToLogTo;
    private double totalRevenue = 0;

    public TotalRevenueFileOutput() throws IOException {
        fileToLogTo = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
    }
    
    /**
     * Takes a exception with its message and then adds date/time to the message. It is then printed to the log file.
     * 
     * @param exception The exception that shall be logged to the file.
     */
    private void logNewSaleToFile(double totalPriceFromMostRecentSale) {
        StringBuilder logMessageBuilder = new StringBuilder();
        logMessageBuilder.append("A new sale was made at time: " + createStringForTimeAndDate() + "\n");
        logMessageBuilder.append("The total price of the sale was: " + totalPriceFromMostRecentSale + " SEK\n");
        logMessageBuilder.append("Total profits since program started: " + totalRevenue + " SEK\n");
        fileToLogTo.println(logMessageBuilder);
    }

    /**
     * Creates a string with the current date and time by getting the current date and time and changing the format to be more clear and readable.
     * 
     * @return The current date and time as a string.
     */
    private String createStringForTimeAndDate() {
        LocalDateTime currentDateAndTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        String formattedDateTime = currentDateAndTime.format(formatter);
        return formattedDateTime;
    }

    /**
     * Updates the total revenue with the most recent sale and logs the new sale to the log file.
     * 
     * @param totalPriceFromMostRecentSale The total price of the most recent sale.
     */
    @Override
    public void updateSaleProfits(double totalPriceFromMostRecentSale){
        totalRevenue += totalPriceFromMostRecentSale;
        logNewSaleToFile(totalPriceFromMostRecentSale);
    }
}
