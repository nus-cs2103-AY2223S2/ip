package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    /**
     * Parses a given input string into a LocalDateTime object. The format of the input can be in either formats below.
     * dd/MM/yyyy HHmm
     * HHmm
     * dd/MM/yyyy 
     * If input is given in HHmm format, the parser will default to the input time on current system date.
     * If input is given in dd/MM/yyyy format, the parser will default to 0000 on the input date.
     * 
     * @param dateTime String to be format into LocalDateTime object.
     * @throws DateTimeParseException Thrown when parser is unable to format input string
     * @return LocalDateTime object corresponding to the input string
     */
    public static LocalDateTime parseInput(String dateTime) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            //Empty catch as can attempt to format as LocalTime
        }
        try {
            LocalTime time = LocalTime.parse(dateTime, DateTimeFormatter.ofPattern("HHmm"));
            LocalDateTime dt = time.atDate(LocalDate.now());
            return dt;
        } catch (DateTimeParseException e) {
            //Empty catch as can attempt to format as LocalDate
        }
        try {
            LocalDate date = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDateTime dt = date.atTime(LocalTime.of(0, 0));
            return dt;
        } catch (DateTimeParseException e) {
            //Empty catch as function will raise a DateTimeParseException once it reaches the end.
        }
        throw new DateTimeParseException("Unable to parse datetime", dateTime, 0);
    }

    /**
     * Formats a LocalDateTime object in dd MMM yyyy hh:mma format.
     * @param dt The LocalDateTime object to be formatted.
     * @return The String of the formatted LocalDateTime object.
     */
    public static String formatOutput(LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma"));
    }

    /**
     * Formats a LocalDateTime object in dd/MM/yyyy HHmm format. Used to save TaskList into a text file.
     * @param dt The LocalDateTime object to be formatted.
     * @return The String of the formatted LocalDateTime object.
     */
    public static String formatSave(LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}
