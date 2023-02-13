package utils;

import dukeexception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Local date time formatting.
 */
public class DateTime {

    /**
     * Returns the localDateTime type of the date and time.
     *
     * @param date Date which is the string type.
     * @return LocalDateTime type of the date and time.
     * @throws DukeException Checks the validation of input.
     */
    public static LocalDateTime getDateTime(String date) throws DukeException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
            return localDateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("\t Wrong Date Format, please write in yyyy-MM-dd HHmm.\n");
        }
    }

    /**
     * Formats the date time input.
     *
     * @param date Date which is the string type.
     * @return String type of date time.
     * @throws DukeException Checks the validation of input.
     */
    public static String dateFormatter(String date) throws DukeException {
        try {
            LocalDateTime localDateTime = getDateTime(date);
            return localDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            throw new DukeException("\t Wrong Date Format, please write in yyyy-MM-dd HHmm.\n");
        }
    }

}
