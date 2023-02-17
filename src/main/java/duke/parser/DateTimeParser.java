package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses datetime and makes sure it is in the format suitable for this app: {yyyy-MM-dd HH:mm}.
 */
public class DateTimeParser {
    /**
     * Returns the valid format of the datetime of the Task.
     * Valid format: {yyyy-MM-dd HH:mm}
     *
     * @param input provided by user.
     * @return Datetime of Task in valid format.
     */
    public static LocalDateTime parse(String input) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(input, formatter);
    }
}
