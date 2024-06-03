package se.kth.iv1350.amazingpos.main.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for taking the total revenue from all sales since the program started and logging it to a file.
 */
public class TotalRevenueFileOutput extends TotalRevenueOut{
    private static final String LOG_FILE_NAME = "amazingpos-saleprofitslog.txt";
    private PrintWriter fileToLogTo;

    public TotalRevenueFileOutput() throws IOException {
        fileToLogTo = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
    }
    
    /**
     * Takes a exception with its message and then adds date/time to the message. It is then printed to the log file.
     * 
     * @param exception The exception that shall be logged to the file.
     */
    private void logNewSaleToFile(double totalRevenue, double totalPriceFromMostRecentSale) {
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
     * Logs the new sale to the log file.
     * 
     * @param totalPriceFromMostRecentSale The total price of the most recent sale.
     * @param totalRev The total revenue of all sales since the program started.
     */
    @Override
    protected void doShowTotalIncome(double totalRev, double totalPriceFromMostRecentSale) throws Exception {
        logNewSaleToFile(totalRev, totalPriceFromMostRecentSale);
    }

    /**
     * Prints error message to the console if the log file could not be written to.
     * 
     * @param e The exception that casued the error.
     * @param errorMessages The error message formatter that will format the error message.
     */
    @Override
    protected void handelErrors(Exception e, ErrorMessageFormatter errorMessages){
        System.out.println(errorMessages.createFormattedErrorMessage("Could not log new sale to file."));
    }
}
