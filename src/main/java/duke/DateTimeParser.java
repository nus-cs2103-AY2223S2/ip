package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

/**
 * Represents a Parser to parse date and time.
 */
public class DateTimeParser {

    /**
     * Constructs an instance of Parser.
     */
    public DateTimeParser() {
        // Empty
    }

    /**
     * Parses a string representation of a date into LocalDate.
     * Expects input to be formatted as "YYYY-MM-DD" (fixed to "-" separator).
     *
     * @param dateStr String representation of a date.
     * @return Date in a LocalDate instance.
     */
    private LocalDate parseDate(String dateStr) throws DateTimeParseException {
        assert !dateStr.isBlank() : "Invalid string, cannot parse date";
        return LocalDate.parse(dateStr);
    }

    // TODO: need to catch exceptions
    /**
     * Parses a string representation of a date into LocalDate.
     * Expects input to be formatted as "YYYY-MM-DD" but separator is arbitrary.
     * Arbitrary separator needs to be specified.
     *
     * @param dateStr   String representation of a date.
     * @param separator Separator used to separate the day, month, year.
     * @return Date in a LocalDate instance.
     */
    private LocalDate parseDate(String dateStr, char separator) {
        assert !dateStr.isBlank() : "Invalid string, cannot parse date";

        String[] date = dateStr.split(String.valueOf(separator));
        int[] dateInfo = Stream.of(date).mapToInt(Integer::parseInt).toArray();
        return LocalDate.of(dateInfo[0], dateInfo[1], dateInfo[2]);
    }

    /**
     * Parses a string representation of a time into LocalTime.
     * Expects input to be formatted as "HH:MM:SS" or "HH:MM" (fixed to ':' separator).
     *
     * @param timeStr String representation of a time.
     * @return Time in a LocalTime instance.
     */
    private LocalTime parseTime(String timeStr) throws DateTimeParseException {
        assert !timeStr.isBlank() : "Invalid string, cannot parse time";
        return LocalTime.parse(timeStr);
    }

    // TODO: need to catch exceptions
    /**
     * Parses a string representation of a time into LocalTime.
     * Expects input to be formatted as "HH:MM:SS" or "HH:MM" but separator is arbitrary.
     * Arbitrary separator needs to be specified.
     *
     * @param timeStr   String representation of a time.
     * @param separator Separator used to separate the hour, minute, (second).
     * @return Time in a LocalTime instance.
     */
    private LocalTime parseTime(String timeStr, char separator) {
        assert !timeStr.isBlank() : "Invalid string, cannot parse time";

        String[] time = timeStr.split(String.valueOf(separator));
        int[] timeInfo = Stream.of(time).mapToInt(Integer::parseInt).toArray();
        if (timeInfo.length == 2) {
            return LocalTime.of(timeInfo[0], timeInfo[1]);
        } else {
            return LocalTime.of(timeInfo[0], timeInfo[1], timeInfo[2]);
        }
    }

    /**
     * Parses a string representation of a date and time into LocalDateTime.
     * Expects input to be formatted as "YYYY-MM-DD HH:MM:SS" or "YYYY-MM-DD HH:MM" (fixed to ' ' Separator).
     *
     * @param str String representation of a date & time.
     * @return Date and time in a LocalDateTime instance.
     */
    public LocalDateTime parseDateTime(String str) throws DateTimeParseException {
        return parseDateTime(str, ' ');
    }

    /**
     * Parses a string representation of a date and time into LocalDateTime.
     * Expects input to be formatted as "YYYY-MM-DD HH:MM:SS" or "YYYY-MM-DD HH:MM" but separator is arbitrary.
     * Arbitrary separator (between date and time) needs to be specified.
     *
     * @param str       String representation of a date & time.
     * @param separator Character separating the date and time.
     * @return Date and time in a LocalDateTime instance.
     * @throws DateTimeParseException When str is provided in an invalid format.
     */
    public LocalDateTime parseDateTime(String str, char separator) throws DateTimeParseException {
        assert !str.isBlank() : "Invalid string, cannot parse date/time";

        String[] s = str.split(String.valueOf(separator));
        return LocalDateTime.of(parseDate(s[0]), parseTime(s[1]));
    }
}
