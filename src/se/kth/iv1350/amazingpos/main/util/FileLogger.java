package se.kth.iv1350.amazingpos.main.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for the log.
 */
public class FileLogger {
    private static final String LOG_FILE_NAME = "amazingpos-errorlog.txt";
    private PrintWriter logFile;
    
    public FileLogger() throws IOException {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
    }
    
    /**
     * Writes a log entry describing a thrown exception.
     * 
     * @param exception The exception that shall be logged.
     */
    public void logException(Exception exception) {
        StringBuilder logMsgBuilder = new StringBuilder();
        logMsgBuilder.append(createTime());
        logMsgBuilder.append(", Exception was thrown: ");
        logMsgBuilder.append(exception.getMessage());
        logFile.println(logMsgBuilder);
        exception.printStackTrace(logFile);
        logFile.println("\n");
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
