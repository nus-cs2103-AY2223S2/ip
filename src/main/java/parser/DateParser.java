package parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dukeexception.InvalidDateException;

/**
 * Parser for datetime objects.
 */
public class DateParser {
    private static final String PARSE_DATE_FORMAT = "yyyy-MM-dd";
    private static final String PARSE_DATETIME_FORMAT = PARSE_DATE_FORMAT + " HH:mm";
    private static final String OUTPUT_DATE_FORMAT = "dd MMM yyyy";
    private static final String OUTPUT_DATETIME_FORMAT = OUTPUT_DATE_FORMAT + " @ HH:mm";
    private static final String STORAGE_DATETIME_FORMAT = PARSE_DATETIME_FORMAT;

    /**
     * Parses raw dates into the LocalDateTime format.
     * @param rawDateString Date that needs to be parsed.
     * @return The datetime as a LocalDateTime object.
     */
    public static LocalDateTime parse(String rawDateString) {
        switch (rawDateString) {
        case "now":
            return LocalDateTime.now();
        case "today":
            return LocalDate.now().atTime(0, 0);
        case "tomorrow":
            return LocalDate.now().plusDays(1).atTime(0, 0);
        case "next week":
            return LocalDate.now().plusDays(7).atTime(0, 0);
        default:
            break;
        }
        try {
            if (rawDateString.strip().split(" ").length == 1) {
                return LocalDate.parse(rawDateString).atTime(0, 0);
            }
            return LocalDateTime.parse(rawDateString, DateTimeFormatter.ofPattern(PARSE_DATETIME_FORMAT));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Formats the datetime object for printing to System.out.
     * @param datetime The datetime object to be formatted.
     * @return A String representing the datetime object.
     */
    public static String formatDateToPrint(LocalDateTime datetime) {
        if (datetime.getHour() + datetime.getMinute() == 0) {
            return datetime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT));
        }
        return datetime.format(DateTimeFormatter.ofPattern(OUTPUT_DATETIME_FORMAT));
    }

    /**
     * Formats the datetime object for storing in the text file.
     * In particular, undoes the parse operation.
     * @param datetime The datetime object to be formatted.
     * @return A String representing the datetime object.
     */
    public static String formatDateToStore(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern(STORAGE_DATETIME_FORMAT));
    }
}
