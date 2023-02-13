package henz.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An abstract class to make sense of the datetime from the user.
 */
public abstract class DateTimeParser {
    private static DateTimeFormatter formatFrom = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static DateTimeFormatter formatTo = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");

    /**
     * Returns the formatted datetime string. This method format datetime string
     * from format yyyy-MM-dd HHmm to MMM dd yyyy, h:mma.
     * e.g. 2019-02-06 1400 will be formatted to Aug 06 2019, 2:00PM
     * @param datetimeString the datetime string from the user.
     * @return the string of the formatted datetime
     * @throws DateTimeParseException
     */
    public static String parse(String datetimeString) throws DateTimeParseException {
        LocalDateTime date = LocalDateTime.parse(datetimeString, formatFrom);
        return date.format(DateTimeParser.formatTo);
    }

    /**
     * Checks if the datetime string is valid for formatting.
     * @param datetimeString the datetime string from the user.
     * @return the string of the formatted datetime
     */
    public static boolean isValidDateTimeFormat(String datetimeString) {
        try {
            LocalDateTime.parse(datetimeString, formatFrom);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Datetime has to be in the following format: yyyy-mm-dd HHmm");
        }

        return false;
    }

    /**
     * Displays error message of DateTimeParseException on UI.
     */
    public static void showDateTimeParseErrorMessage() {
        System.out.println("Datetime has to be in the following format: yyyy-mm-dd HHmm");
    }
}
