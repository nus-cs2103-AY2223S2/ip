package aqua.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import aqua.exception.IllegalSyntaxException;


/** Utility class for dates and time. */
public class DateUtils {
    /**
     * Parses String of the following format to a LocalDateTime.
     * <ul>
     * <li> {@code yyyy-MM-ddThh:mm}
     * <li> {@code yyyy-MM-dd hhmm}
     * <li> {@code yyyy-MM-dd} (assume time to be 0000)
     * </ul>
     *
     * @param dateString - the date String to parse.
     * @return the parsed LocalDateTime.
     * @throws IllegalSyntaxException if the given String cannot be parsed.
     */
    public static LocalDateTime parse(String dateString) throws IllegalSyntaxException {
        try {
            // yyyy-MM-ddThh:mm default format
            return LocalDateTime.parse(dateString);
        } catch (DateTimeParseException parseEx) {
            try {
                if (dateString.matches("\\d{4}-\\d{1,2}-\\d{1,2} \\d{4}")) {
                    // yyyy-MM-dd hhmm format
                    return LocalDateTime.parse(
                            dateString,
                            DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
                } else if (dateString.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
                    // yyyy-MM-dd format
                    return LocalDateTime.parse(
                        dateString + " 0000",
                        DateTimeFormatter.ofPattern("yyyy-M-d HHmm")
                    );
                }
            } catch (DateTimeParseException ex) {
                // random numbers eg. 0000-00-00
                throw new IllegalSyntaxException(
                    String.format("I do not understand when this is [%s]", dateString)
                );
            }
        }
        throw new IllegalSyntaxException(
            String.format("I do not understand when this is [%s]", dateString)
        );
    }


    /**
     * Formats the date to a the format {@code d LLL yyyy HHmm}.
     *
     * @param time - the time to format.
     * @return the formatted date.
     */
    public static String formatNice(LocalDateTime time) {
        return time.format(DateTimeFormatter.ofPattern("d LLL yyyy HHmm"));
    }


    public static boolean isIntersecting(LocalDateTime s1, LocalDateTime e1, LocalDateTime s2, LocalDateTime e2) {
        return !(s1.equals(e2) || e1.equals(s2) || s1.isAfter(e2) || e1.isBefore(s2));
    }
}
