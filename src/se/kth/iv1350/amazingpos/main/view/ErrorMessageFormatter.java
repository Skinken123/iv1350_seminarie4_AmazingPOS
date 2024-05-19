package se.kth.iv1350.amazingpos.main.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for formatting error messages and then sending them to the user.
 */
public class ErrorMessageFormatter {

      /**
     * Takes a error message and formats it with additional useful information.
     * It adds the time of the error as well as a prefix to the error message.
     * 
     * @param errorMessage The error message.
     */
    public String createFormattedErrorMessage(String errorMessage) {
        StringBuilder formattedErrorMesssageBuilder = new StringBuilder();
        formattedErrorMesssageBuilder.append("REPORTED ERROR: ");
        formattedErrorMesssageBuilder.append(errorMessage);
        formattedErrorMesssageBuilder.append("\n");
        formattedErrorMesssageBuilder.append("Time and date of ERROR: ");
        formattedErrorMesssageBuilder.append(createStringForTimeAndDate());
        //System.out.println(formattedErrorMesssageBuilder);
        return formattedErrorMesssageBuilder.toString();
    }

    /**
     * Creates a string with the current date and time.
     * 
     * @return The current date and time as a string.
     */
    private String createStringForTimeAndDate() {
        LocalDateTime currentDateAndTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        String formattedDateTime = currentDateAndTime.format(formatter);
        return formattedDateTime;
    }
}
