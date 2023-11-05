package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

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
    public static LocalDateTime parse(String input) throws DateTimeParseException, DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime validDateTime = LocalDateTime.parse(input, formatter);
        LocalDateTime currentDateTime = LocalDateTime.now();
        if (validDateTime.compareTo(currentDateTime) < 0) {
            throw new DukeException("Fake Duke only accepts future date time.");
        }
        return validDateTime;
    }
}
