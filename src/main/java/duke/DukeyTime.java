package duke;

import duke.exceptions.DukeyException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Handles operations relating to dates
 */
public class DukeyTime {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Returns a LocalDate from a String
     * @param s the String containing the date
     */
    public static LocalDate getDateFromString(String s) throws DukeyException {
        LocalDate date = LocalDate.now();
        try {
            date = LocalDate.parse(s, formatter);
        } catch (DateTimeException e) {
            throw new DukeyException("Error! Invalid date!");
        }
        return date;
    }

    /**
     * Returns a string representation of a date
     * @param date the date to be represented
     */
    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
}
