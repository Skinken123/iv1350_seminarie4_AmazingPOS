package se.kth.iv1350.amazingpos.test.view;

import se.kth.iv1350.amazingpos.main.view.ErrorMessageFormatter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

/**
 * This is the class that will test all of the non constructor/get/set methods in the ErrorMessageFormatter class.
 */
public class ErrorMessageFormatterTest {
    private ErrorMessageFormatter errorMessageFormatter;

    /**
     * This method sets up the test class before each test method is run.
     */
    @BeforeEach
    public void setUp() {
        errorMessageFormatter = new ErrorMessageFormatter();
    }

    /**
     * This method creates test strings with the expected return of the method and compares it to the actual return of the method.
     */
    @Test
    public void testFormatErrorMessage() {
        String errorMessage = "This is an error message";
        String result = errorMessageFormatter.createFormattedErrorMessage(errorMessage);
        String expected = "REPORTED ERROR: This is an error message";
        String alsoExpected = "Time and date of ERROR: ";

        LocalDateTime currentDateAndTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        String formattedDateTime = currentDateAndTime.format(formatter);

        assertTrue(result.contains(expected), "The error message is not correct");
        assertTrue(result.contains(alsoExpected), "The error message is not correct");
        assertTrue(result.contains(formattedDateTime), "The error message is not correct");
    }

    /**
     * This method reste the errorMessageFormatter to null after each test method is run.
     */
    @AfterEach
    public void tearDown() {
        errorMessageFormatter = null;
    }
}
