package se.kth.iv1350.amazingpos.main.utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for taking exceptions and turning them into informative messages to then be printed to a log file.
 * The log file is created if it does not already exist and is then appended to. The log file is for the developers to use to find and fix bugs.
 */
public class FileLogger {
    private static final String LOG_FILE_NAME = "amazingpos-errorlog.txt";
    private PrintWriter fileToLogTo;
    
    public FileLogger() throws IOException {
        fileToLogTo = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
    }
    
    /**
     * Takes a exception with its message and then adds date/time to the message. It is then printed to the log file.
     * 
     * @param exception The exception that shall be logged to the file.
     */
    public void logExceptionToFile(Exception exception) {
        StringBuilder logMessageBuilder = new StringBuilder();
        logMessageBuilder.append(createStringForTimeAndDate());
        logMessageBuilder.append(", Exception was thrown: ");
        logMessageBuilder.append(exception.getMessage());
        fileToLogTo.println(logMessageBuilder);
        exception.printStackTrace(fileToLogTo);
        fileToLogTo.println("\n");
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
}
