package duke.logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Handles strings that are supposed to be dates and formats them properly.
 * @author lukkesreysandeur
 */
public class DateHandler {
    /**
     * Parses a user entered string into a date.
     *
     * @param input The time frame entered by the user.
     * @return A String representing the date if a date was entered;
     *         the original input otherwise.
     */
    public static String parse(String input) {
        try {
            LocalDate date = LocalDate.parse(input);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch(DateTimeParseException e) {
            return input;
        }
    }


}
