package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    public static LocalDateTime parseInput(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) { }
        try {
            LocalTime time = LocalTime.parse(dateTime, DateTimeFormatter.ofPattern("HHmm"));
            LocalDateTime dt = time.atDate(LocalDate.now());
            return dt;
        } catch (DateTimeParseException e) { }
        try {
            LocalDate date = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDateTime dt = date.atTime(LocalTime.of(0, 0));
            return dt;
        } catch (DateTimeParseException e) { }
        throw new DateTimeParseException("Unable to parse datetime", dateTime, 0);
    }
    
    public static String formatOutput(LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma"));
    }
}
