package duke.datetime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class DateTime {

    /**
     * date of the instance
     */
    public LocalDate date;

    /**
     * time of the instance
     */
    public LocalTime time;

    /**
     * An instance of date and time.
     *
     * @param input Input of date and time in "YYYY-MM-DD TTTT".
     * @throws IllegalArgumentException When input is not of correct format.
     */
    public DateTime(String input) throws IllegalArgumentException {

        String[] splitInput = input.split(" ");
        if (splitInput.length != 2) {
            throw new IllegalArgumentException("Invalid format for date and time, " +
                    "expected: \"YYYY-MM-DD TTTT\" actual: " + input);
        }
        date = parseDate(splitInput[0]);
        time = parseTime(splitInput[1]);
    }

    /**
     * Returns the string representation of this datetime instance.
     *
     * @return String representation of datetime.
     */
    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG))
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
            throw new IllegalArgumentException("Invalid format for date, " +
                    "expected: \"YYYY-MM-DD\" actual: " + date);
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
            throw new IllegalArgumentException("Invalid format for time, " +
                    "expected: \"TTTT\" actual: " + time);
        }
    }
}
