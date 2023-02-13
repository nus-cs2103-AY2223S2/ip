package duke.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates useful time operations for Duke methods.
 */
public class Time {
    /**
     * Format of the stored date and time.
     */
    public static final String STORE_DATE_TIME_FORMAT = "dd/MM/yyyy HHmm";
    /**
     * Format of the displayed date and time.
     */
    public static final String DISPLAY_DATE_TIME_FORMAT = "dd LLL yyyy HHmm";

    /**
     * Parses a string date time format in <code>STORE_DATE_TIME_FORMAT</code>
     * and returns it of type <code>LocalDateTime</code>.
     * @param dateTime <code>String</code> format of a date time.
     * @return <code>LocalDateTime</code> format of a date time.
     * @throws DateTimeParseException If users enters the date time in the wrong format.
     */
    public static LocalDateTime getLocalDateTime(String dateTime)
            throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STORE_DATE_TIME_FORMAT);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTime,
                formatter);
        return localDateTime;
    }

    /**
     * Converts a <code>LocalDateTime</code> in <code>DISPLAY_DATE_TIME_FORMAT</code>
     * to a <code>String</code>
     * and returns it of type <code>LocalDateTime</code>.
     * @param dateTime <code>String</code> format of a date time.
     * @return <code>LocalDateTime</code> format of a date time.
     */
    public static String getDisplayFormatDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DISPLAY_DATE_TIME_FORMAT);
        return dateTime.format(formatter);
    }

    public static LocalDateTime getRemindDefaultLocalDateTime(String dateTime) {
        return getLocalDateTime(dateTime).toLocalDate().atTime(0, 0);
    }

    public static String getFileFormatDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(STORE_DATE_TIME_FORMAT);
        return dateTime.format(formatter);
    }
}
