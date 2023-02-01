package utils;

import dukeexception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles all date formatting.
 */
public class DateTimeUtils {
    /**
     * Static method to format user input for date.
     *
     * @param date String that stores date.
     * @return String date that is formatted.
     * @throws DukeException Checks valid input from user.
     */
    public static String dateFormatter(String date) throws DukeException {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return localDate.format(DateTimeFormatter.ofPattern("dd MMM uuuu"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong Date Format, please write in yyyy-MM-dd");
        }
    }
}
