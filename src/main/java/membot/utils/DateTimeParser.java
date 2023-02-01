package membot.utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A helper class that helps with <code>String</code> to/from <code>LocalDateTime</code> conversion.
 */
public class DateTimeParser {
    private static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm";
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Converts a <code>String</code> representation of a dateTime to a <code>LocalDateTime</code>
     * object.
     *
     * @param dateTime A <code>String</code> representation of a dateTime.
     * @return A <code>LocalDateTime</code> object that corresponds to the <code>String</code> representation.
     * @throws DateTimeParseException If the <code>String</code> representation
     *      cannot be converted to a <code>LocalDateTime</code> object.
     */
    public static LocalDateTime parse(String dateTime) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DateTimeParser.DATETIME_FORMAT));
        } catch (DateTimeParseException e1) {
            return LocalDate.parse(dateTime, DateTimeFormatter.ofPattern(DateTimeParser.DATE_FORMAT)).atStartOfDay();
        }
    }

    /**
     * Converts a <code>LocalDateTime</code> to a <code>String</code> representation.
     *
     * @param dateTime A <code>LocalDateTime</code> object.
     * @return A <code>String</code> representation of the <code>LocalDateTime</code> object.
     */
    public static String format(LocalDateTime dateTime) {
        try {
            return dateTime.format(DateTimeFormatter.ofPattern(DateTimeParser.DATETIME_FORMAT));
        } catch (DateTimeException e) {
            return dateTime.format(DateTimeFormatter.ofPattern(DateTimeParser.DATE_FORMAT));
        }
    }
}
