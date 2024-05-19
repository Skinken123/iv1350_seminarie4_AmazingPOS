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
    void createFormattedErrorMessage(String errorMessage) {
        StringBuilder formattedErrorMesssageBuilder = new StringBuilder();
        formattedErrorMesssageBuilder.append(createStringForTimeAndDate());
        formattedErrorMesssageBuilder.append(", REPORTED ERROR: ");
        formattedErrorMesssageBuilder.append(errorMessage);
        System.out.println(formattedErrorMesssageBuilder);
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
