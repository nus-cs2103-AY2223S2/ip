package aqua.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import aqua.exception.SyntaxException;


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
     * @throws SyntaxException if the given String cannot be parsed.
     */
    public static LocalDateTime parse(String dateString) throws SyntaxException {
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
                throw new SyntaxException(
                    String.format("I do not understand when this is [%s]", dateString)
                );
            }
        }
        throw new SyntaxException(
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


    /**
     * Returns if two time ranges are intersecting.
     *
     * @param s1 - start time of the first time range.
     * @param e1 - end time of the first time range.
     * @param s2 - start time of the second time range.
     * @param e2 - end time of the second time range.
     * @return {@code true} if the given time ranges are intersecting and
     *      {@code false} otherwise.
     */
    public static boolean isIntersecting(
                LocalDateTime s1, LocalDateTime e1, LocalDateTime s2, LocalDateTime e2) {
        boolean isStartEndIntersecting = s1.isEqual(s2) || e1.isEqual(e2);
        return !(s1.isEqual(e2) || e1.isEqual(s2) || s1.isAfter(e2) || e1.isBefore(s2)) || isStartEndIntersecting;
    }


    /**
     * Returns two time ranges are intersecting. The time ranges are considered
     * to be intersecting if they overlap each other or if the start or end of
     * one time range is close enough to the other as specified by the
     * {@code threshold} parameter.
     *
     * @param s1 - start time of the first time range.
     * @param e1 - end time of the first time range.
     * @param s2 - start time of the second time range.
     * @param e2 - end time of the second time range.
     * @param threshold - threshold in minutes of the difference in start and
     *      end times of one range to the other respectively for them to be
     *      considered intersecting.
     * @return {@code true} if the given time ranges are intersecting and
     *      {@code false} otherwise.
     */
    public static boolean isIntersecting(LocalDateTime s1, LocalDateTime e1, LocalDateTime s2, LocalDateTime e2,
                double threshold) {
        boolean isStartIntersecting = Math.abs(s1.until(s2, ChronoUnit.MINUTES)) <= threshold;
        boolean isEndIntersecting = Math.abs(e1.until(e2, ChronoUnit.MINUTES)) <= threshold;
        boolean isStartEndIntersecting = isStartIntersecting || isEndIntersecting;
        return !(s1.isEqual(e2) || e1.isEqual(s2) || s1.isAfter(e2) || e1.isBefore(s2)) || isStartEndIntersecting;
    }


    /**
     * Returns the time that is the start of the week of the given time. The
     * start of the week is considered as {@link #DEFAULT_START_OF_WEEK}.
     *
     * @param time - a time.
     * @return the time that is the start of the week of the given time.
     */
    public static LocalDateTime toStartOfWeek(LocalDateTime time) {
        return toStartOfWeek(time, DEFAULT_START_OF_WEEK);
    }


    /**
     * Returns the time that is the start of the week of the given time.
     *
     * @param time - a time.
     * @param weekStart - the {@code DayOfWeek} that is the start of the week.
     * @return the time that is the start of the week of the given time.
     */
    public static LocalDateTime toStartOfWeek(LocalDateTime time, DayOfWeek weekStart) {
        int offset = time.getDayOfWeek().getValue() - weekStart.getValue();
        if (offset < 0) {
            offset += DayOfWeek.SUNDAY.getValue();
        }
        return toStartOfDay(time.minus(offset, ChronoUnit.DAYS));
    }


    /**
     * Returns the time that is the start of the day of the given time.
     *
     * @param time - a time.
     * @return the time that is the start of the day of the given time.
     */
    public static LocalDateTime toStartOfDay(LocalDateTime time) {
        return LocalDateTime.of(
                time.getYear(),
                time.getMonthValue(),
                time.getDayOfMonth(),
                0, 0);
    }
}
