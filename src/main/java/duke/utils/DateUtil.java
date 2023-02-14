package duke.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

/**
 * A utility class that provide useful functions for handling date objects.
 */
public class DateUtil {

    // Solution below adapted from
    // https://www.waitingforcode.com/java-8/managing-different-date-time-formats-datetimeformatterbuilder/read
    public static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("MMM d yyyy H:m"))
            .appendOptional(DateTimeFormatter.ofPattern("MMM d yyyy"))
            .toFormatter();

    /**
     * Attempts to convert a string to a LocalDateTime object.
     * Solution below adapted from https://stackoverflow.com/a/48280447
     *
     * @param input string for conversion
     * @return a date object
     * @throws DateTimeParseException if the provide input does not follow the date
     *                                format
     * @see LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(String input) throws DateTimeParseException {
        LocalDateTime dateTime;
        TemporalAccessor temporalAccessor = DATE_FORMATTER.parseBest(input, LocalDateTime::from, LocalDate::from);
        if (temporalAccessor instanceof LocalDateTime) {
            dateTime = (LocalDateTime) temporalAccessor;
        } else {
            dateTime = ((LocalDate) temporalAccessor).atStartOfDay();
        }
        return dateTime;
    }

    /**
     * Converts date object to string.
     *
     * @param date object for conversion
     * @return a date in string
     * @see LocalDateTime
     */
    public static String dateToString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    /**
     * Converts date object to string.
     *
     * @param date object for conversion
     * @return a date in formatted string
     */
    public static String dateToPrettyString(LocalDateTime date) {
        if (date.toLocalTime().equals(LocalTime.of(0, 0))) {
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        }
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy H:m"));
    }
}
