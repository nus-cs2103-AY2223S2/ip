package duke.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A utility class to convert date in {@code String} format to {@code LocalDate} format and
 * vice-versa.
 */
public class DukeDate {
    private static final DateTimeFormatter INPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Parses a {@code String} representation of date into a {@code LocalDate} instance.
     *
     * @param dateString
     * @return A {@code LocalDate} instance
     */
    public static LocalDate parseDateString(String dateString) {
        return LocalDate.parse(dateString, INPUT_DATE_FORMAT);
    }

    /**
     * Returns the String representation of the {@code LocalDate} instance.
     *
     * @param date
     * @return The {@code String} representation of the {@code date} instance in the format of MMM
     *         dd yyyy.
     */
    public static String convertDateToString(LocalDate date) {
        return date.format(OUTPUT_DATE_FORMAT);
    }
}
