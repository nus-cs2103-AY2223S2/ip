package duke.command.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.exceptions.InvalidParameterError;

/**
 * A utility to parse datetime strings.
 */
public class DateTimeStringParser {
    public static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");
    /**
     * Return a LocalDateTime instance with the date and time from the parsed dateTimeString.
     * Uses the defaultTime parameter for the instance if no time is supplied.
     * @param dateTimeString The string to parse.
     * @param defaultTime Default time, if string does not contain time.
     * @return A LocalDateTime instance with the parsed date and time.
     * @throws InvalidParameterError if the string is not in the expected d/MM/yyyy HHmm or d/MM/yyyy pattern.
     */
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
