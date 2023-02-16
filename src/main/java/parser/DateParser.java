package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

/**
 * This class parses strings into LocalDateTime format
 */
public class DateParser {
    /**
     * Converts String into LocalDateTime format
     * 
     * @param s
     * @return date and time of parsed string
     */
    public static LocalDateTime stringToDate(String s) {
        try {
            return LocalDateTime.parse(s, DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
        } catch (DateTimeParseException e) {
            // "* could not be parsed at index *" -- "please enter valid date format: yyyy-mm-dd hh:mm(:ss)"
            throw new IllegalArgumentException(e.getMessage());
            // return LocalDateTime.now();
        }
    }
}