package duke.parser;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exception.InvalidInputException;

/**
 * A TimeHandler class that encapsulates all the methods that related to handling the time related string.
 */
public class TimeHandler {
    //@@author Yufannn-reused
    //Reused from https://github.com/RussellDash332/ip/blob/master/src/main/java/stashy/parser/Parser.java
    //with minor modification, it is a pretty good way to organise and extend the acceptable date format.
    private static final String[] ACCEPTABLE_DATETIME_FORMATS = {
        "MMM dd yyyy HHmm", "MMM dd yyyy HH:mm",
        "yyyy-MM-dd'T'HH:mm", "dd/MM/yyyy HHmm",
        "dd/MM/yyyy HH:mm", "yyyy/MM/dd HHmm",
        "yyyy/MM/dd HH:mm", "yyyy/MM/dd'T'HHmm",
        "yyyy/MM/dd'T'HH:mm", "yyyy-MM-dd HHmm",
        "yyyy-MM-dd HH:mm", "dd MMM yyyy HHmm",
        "dd MMM yyyy HH:mm", "MMM dd, yyyy HHmm",
        "MMM dd, yyyy HH:mm", "dd-mm-yyyy HHmm"
    };
    //@@author

    private static final String[] ACCEPTABLE_DATE_FORMATS = {
        "MMM dd yyyy", "yyyy-MM-dd", "dd/MM/yyyy", "yyyy/MM/dd",
        "dd MMM yyyy", "MMM dd, yyyy", "dd-mm-yyyy"
    };

    //@@author Yufannnn-reused
    //Reused from https://github.com/wweqg/ip/blob/master/src/main/java/duke/parser/Parser.java
    //with minor modification, it is a pretty clean and concise regular expression for general instructions
    /**
     * This method is used to parse a date string to {@link LocalDate}
     * The method will iterate through the list of acceptable date formats {@link #ACCEPTABLE_DATE_FORMATS}
     * and try to parse the date string to {@link LocalDate} using {@link DateTimeFormatter#ofPattern(String)}
     * If the parsing is successful, it will return the parsed {@link LocalDate}
     * If the parsing fails for all the acceptable date formats, the method will throw a {@link InvalidInputException}
     *
     * @param date the date string to be parsed
     * @return the parsed {@link LocalDate}
     * @throws InvalidInputException when the date string is not in any of the acceptable date formats
     *
     */
    public static LocalDate parseToLocalDate(String date) throws InvalidInputException {
        for (String dateFormat : ACCEPTABLE_DATE_FORMATS) {
            try {
                return LocalDate.parse(date, DateTimeFormatter.ofPattern(dateFormat));
            } catch (Exception e) {
                // Go to the next dateFormat
            }
        }
        throw new InvalidInputException(ErrorMessage.INVALID_DATE_ERROR);
    }

    /**
     * Parses a string to a LocalDateTime object using the acceptable date time formats defined
     * in {@link #ACCEPTABLE_DATETIME_FORMATS}.
     *
     * @param date The date string to be parsed
     * @return The parsed LocalDateTime object
     * @throws InvalidInputException if the date string does not match any of the acceptable date time formats
     */
    public static LocalDateTime parseToLocalDateTime(String date) throws InvalidInputException {
        for (String dateTimeFormat : ACCEPTABLE_DATETIME_FORMATS) {
            try {
                return LocalDateTime.parse(date,
                        DateTimeFormatter.ofPattern(dateTimeFormat));
            } catch (Exception e) {
                // Go to the next dateTimeFormat
            }
        }
        throw new InvalidInputException(ErrorMessage.INVALID_DATETIME_ERROR);
    }
    //@@author

    //@@author Yufannnn-reused
    //Reused from https://stackoverflow.com/questions/3471397/how-can-i-pretty-print-a-duration-in-java
    //with minor modification, a nice way to print out duration in a human-readable way
    /**
     * Returns the human-readable format of a given duration.
     *
     * @param duration the duration to be converted to human-readable format
     * @return the human-readable format of the duration
     */
    public static String humanReadableFormat(Duration duration) {
        return duration.toString()
                .substring(2)
                .replaceAll("(\\d[HMS])(?!$)", "$1 ")
                .toLowerCase();
    }
    //@@author
}
