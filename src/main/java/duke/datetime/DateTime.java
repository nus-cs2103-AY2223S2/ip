package duke.datetime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Date and time representing a particular instant.
 */
public class DateTime {

    /** Raw date time */
    private final String raw;

    /** Date of the instance */
    private final LocalDate date;

    /** Time of the instance */
    private final LocalTime time;

    /**
     * Constructs an instance of date and time.
     *
     * @param input Input of date and time in "YYYY-MM-DD TTTT".
     * @throws IllegalArgumentException When input is not of correct format.
     */
    public DateTime(String input) throws IllegalArgumentException {
        raw = input;
        String[] splitInput = input.split(" ");
        if (splitInput.length != 2) {
            throw new IllegalArgumentException("Invalid format for date and time, "
                    + "expected: \"YYYY-MM-DD TTTT\" actual: " + input);
        }
        date = parseDate(splitInput[0]);
        time = parseTime(splitInput[1]);
    }

    /**
     * Returns the raw date time passed into the constructor of this datetime instance.
     *
     * @return Raw date time of this datetime instance.
     */
    public String getRaw() {
        return raw;
    }

    /**
     * Returns the string representation of this datetime instance.
     *
     * @return String representation of datetime.
     */
    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofPattern("dd MM yyyy"))
                + " " + time;
    }

    /**
     * Parses date in String to LocalDate.
     *
     * @param date Date to parse.
     * @return Parsed LocalDate.
     */
    private LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid format for date, "
                    + "expected: \"YYYY-MM-DD\" actual: " + date);
        }
    }

    /**
     * Parses time in String to LocalTime.
     *
     * @param time Time to parse.
     * @return Parsed LocalTime.
     */
    private LocalTime parseTime(String time) {
        if (!time.contains(":")) {
            time = time.substring(0, 2) + ":"
                    + time.substring(2);
        }
        try {
            return LocalTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid format for time, "
                    + "expected: \"TTTT\" actual: " + time);
        }
    }

    /**
     * Returns whether this datetime represents the same date and time as another datetime.
     *
     * @param other Other object.
     * @return true if other object is a datetime and represents the same date and time
     *         as this datetime.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof DateTime)) {
            return false;
        }
        DateTime dateTime = (DateTime) other;
        return toString().equals(dateTime.toString());
    }
}
