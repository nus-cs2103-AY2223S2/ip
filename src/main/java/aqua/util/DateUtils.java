package aqua.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import aqua.exception.IllegalSyntaxException;


/** Utility class for dates and time. */
public class DateUtils {
    public static final DayOfWeek DEFAULT_START_OF_WEEK = DayOfWeek.SUNDAY;

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
        boolean isStartEndIntersecting = s1.isEqual(s2) || e1.isEqual(e2);
        return !(s1.isEqual(e2) || e1.isEqual(s2) || s1.isAfter(e2) || e1.isBefore(s2)) || isStartEndIntersecting;
    }


    public static boolean isIntersecting(LocalDateTime s1, LocalDateTime e1, LocalDateTime s2, LocalDateTime e2,
                double threshold) {
        boolean isStartIntersecting = Math.abs(s1.until(s2, ChronoUnit.MINUTES)) <= threshold;
        boolean isEndIntersecting = Math.abs(e1.until(e2, ChronoUnit.MINUTES)) <= threshold;
        boolean isStartEndIntersecting = isStartIntersecting || isEndIntersecting;
        return !(s1.isAfter(e2) || e1.isBefore(s2)) || isStartEndIntersecting;
    }


    public static LocalDateTime getStartOfWeek(LocalDateTime time) {
        return toStartOfWeek(time, DEFAULT_START_OF_WEEK);
    }


    public static LocalDateTime toStartOfWeek(LocalDateTime time, DayOfWeek weekStart) {
        int offset = time.getDayOfWeek().getValue() - weekStart.getValue();
        if (offset < 0) {
            offset += DayOfWeek.SUNDAY.getValue();
        }
        return toStartOfDay(time.minus(offset, ChronoUnit.DAYS));
    }


    public static LocalDateTime toStartOfDay(LocalDateTime time) {
        return LocalDateTime.of(
                time.getYear(),
                time.getMonthValue(),
                time.getDayOfMonth(),
                0, 0);
    }
}
