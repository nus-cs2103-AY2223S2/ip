package DukeHelpfulCode.Utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DatetimeFormatter {
    public static LocalDateTime formatDateTime(String datetime, String format){
        LocalDateTime dt = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            dt = LocalDateTime.parse(datetime, formatter);
        } catch (DateTimeParseException e) {}
        return dt;
    }
}
