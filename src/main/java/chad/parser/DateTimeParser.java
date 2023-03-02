package chad.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chad.exception.InvalidDateTimeException;

/**
 * Class to parse the date into valid format.
 */
public class DateTimeParser {
    private static DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Returns formatted datetime string.
     * @param dateTime datetime string representation from user.
     * @return formatted date time string.
     */
    public static LocalDateTime parse(String dateTime) {
        try {
            LocalDateTime date = LocalDateTime.parse(dateTime, validFormat);
            return date;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }
}
