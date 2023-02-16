package saturday.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

import saturday.exceptions.SaturdayException;
/**
 * Utility class to parse and print date and time in various formats
 */
public class DateTimeParser {

    /**
     * Array of date and time formats to try when parsing a date string
     */
    private static final String[] DATE_TIME_FORMATS = {
            "dd-MM-yyyy",
            "dd-MM-yyyy",
            "dd/MM/yyyy",
            "dd.MM.yyyy",
            "dd MM yyyy",
            "dd-MM-yyyy HH:mm",
            "dd/MM/yyyy HH:mm",
            "dd.MM.yyyy HH:mm",
            "dd MM yyyy HH:mm",
            "dd-MM-yyyy hh:mm a",
            "dd/MM/yyyy hh:mm a",
            "dd.MM.yyyy hh:mm a",
            "dd MM yyyy hh:mm a",
            "HH:mm dd-MM-yyyy",
            "HH:mm dd/MM/yyyy",
            "HH:mm dd.MM.yyyy",
            "HH:mm dd MM yyyy",
            "hh:mm a dd-MM-yyyy",
            "hh:mm a dd/MM/yyyy",
            "hh:mm a dd.MM.yyyy",
            "hh:mm a dd MM yyyy"
    };

    /**
     * Parse Date string to TemporalAccessor
     *
     * @param date date string to be parsed
     * @return TemporalAccessor representation of the date
     * @throws SaturdayException if date string is in invalid format
     */
    public static TemporalAccessor parseDate(String date) {
        for (String format : DATE_TIME_FORMATS) {
            try {
                return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
                try {
                    return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
                } catch (DateTimeParseException e1) {
                    // try next format
                }
            }
        }
        throw new SaturdayException("Invalid date format: " + date);
    }

    /**
     * Convert the TemporalAccessor into a String
     *
     * @param dateTime TemporalAccessor to be converted into a String
     * @return String representation of the date
     */
    public static String printDateTime(TemporalAccessor dateTime) {
        String result;
        DateTimeFormatter formatter;
        if (dateTime instanceof LocalDate) {
            formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        } else {
            formatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        }
        result = formatter.format(dateTime);
        return result;
    }

}
