package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    // Format deadline into a Datetime object
    // Format expected is 08/12/2023 1800
    public static LocalDateTime dateTimeParser(String input) throws DukeException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(input, format);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid Date and Time provided");
        }
    }

    public static String datetimeFormatter(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }
}
