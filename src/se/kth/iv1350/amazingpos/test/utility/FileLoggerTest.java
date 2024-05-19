package se.kth.iv1350.amazingpos.test.utility;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.amazingpos.main.integration.ItemIdentifierDoesNotExistException;
import se.kth.iv1350.amazingpos.main.utility.FileLogger;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

public class FileLoggerTest {
    private FileLogger fileLogger;
    private String logFile = "amazingpos-errorlog.txt";

    @BeforeEach
    public void setUp() {
        try {
            fileLogger = new FileLogger();
        } catch (IOException e) {
            fail("Could not create the file logger");
        }
    }

    @Test
    public void testLog() throws IOException {
        ItemIdentifierDoesNotExistException exception = new ItemIdentifierDoesNotExistException(78);
        fileLogger.logExceptionToFile(exception);
        String expected = "Exception was thrown: The item with the identifier 100 does not exist.";
        LocalDateTime currentDateAndTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        String formattedDateTime = currentDateAndTime.format(formatter);

        assertTrue(checkIfLogContains(expected), "The log file does not contain the expected string: " + expected);
        assertTrue(checkIfLogContains(formattedDateTime), "The log file does not contain the expected string: " + formattedDateTime);
    }

    @AfterEach
    public void tearDown() {
        fileLogger = null;
    }

    /**
     * Goes through the log file and checks if the expected string is in the log file.
     * @param expected The string that is expected to be in the log file.
     * @return True if the string is in the log file, false if it is not.
     * @throws IOException If the log file could not be read. 
     */
    private boolean checkIfLogContains(String expected) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(logFile));
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(expected)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }
    
}
