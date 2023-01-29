package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * DateTimeParser class to handle DateTime manipulation.
 *
 * @author Merrick
 */
public class DateTimeParser {
    // Format deadline into a Datetime object
    // Format expected is 08/12/2023 1800

    /**
     * Static method to format datetime string into a LocalDateTime object.
     * @param input String input for datetime.
     * @return LocalDateTime object representing the current datetime.
     * @throws DukeException If the input is not in the write format to be converted.
     */
    public static LocalDateTime dateTimeParser(String input) throws DukeException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(input, format);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date and Time provided");
        }
    }

    /**
     * Takes in the dateTime object to produce the right format to show to the User.
     * @param datetime LocalDateTime object
     * @return String output of the datetime.
     */
    public static String datetimeFormatter(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }
}
