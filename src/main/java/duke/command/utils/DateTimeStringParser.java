package duke.command.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.exceptions.InvalidParameterError;

public class DateTimeStringParser {
    public static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
    public static LocalDateTime parseDateTimeString(String dateTimeString, LocalTime defaultTime)
            throws InvalidParameterError {
        DateTimeFormatter formatterWithTime = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        DateTimeFormatter formatterWithoutTime = DateTimeFormatter.ofPattern("d/MM/yyyy");
        try {
            return LocalDateTime.parse(dateTimeString, formatterWithTime);
        } catch (DateTimeParseException e) {
            try {
                LocalDate date = LocalDate.parse(dateTimeString, formatterWithoutTime);
                return LocalDateTime.of(date, defaultTime);
            } catch (DateTimeParseException ex) {
                throw new InvalidParameterError("Please enter date as d/MM/yyyy or d/MM/yyyy HHmm");
            }
        }
    }
}
