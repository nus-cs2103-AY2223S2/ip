package alfred.parser;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import alfred.exceptions.AlfredException;

/**
 * Represents a parser that parses a string into its respective format.
 */
public class DateTimeParser {

    /**
     * Parses the date into LocalDate for better representation.
     * @param date The date given by the user.
     * @return The LocalDate object that contains the date.
     * @throws AlfredException The error when the date given by the user is in an invalid format.
     */
    public LocalDate parseIntoLocalDate(String date) throws AlfredException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy");
            return LocalDate.parse(date, format);
        } catch (DateTimeParseException e) {
            throw new AlfredException("The date format should be given as dd/mm/yyyy\n");
        }
    }

    /**
     * Parses the date into LocalDateTime for better representation.
     * @param date The date given by the user.
     * @return The LocalDateTime object that contains the date.
     * @throws AlfredException The error when the date given by the user is in an invalid format.
     */
    public LocalDateTime parseIntoLocalDateTime(String date) throws AlfredException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
            return LocalDateTime.parse(date, format);
        } catch (DateTimeParseException e) {
            throw new AlfredException("The date format should be given as dd/mm/yyyy HHmm\n");
        }
    }
}
