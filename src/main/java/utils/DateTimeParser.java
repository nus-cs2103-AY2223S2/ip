package utils;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeParser {
    private static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm";
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    public static LocalDateTime parse(String dateTime) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DateTimeParser.DATETIME_FORMAT));
        } catch (DateTimeParseException e1) {
            return LocalDate.parse(dateTime, DateTimeFormatter.ofPattern(DateTimeParser.DATE_FORMAT)).atStartOfDay();
        }
    }

    public static String format(LocalDateTime dateTime) {
        try {
            return dateTime.format(DateTimeFormatter.ofPattern(DateTimeParser.DATETIME_FORMAT));
        } catch (DateTimeException e) {
            return dateTime.format(DateTimeFormatter.ofPattern(DateTimeParser.DATE_FORMAT));
        }
    }
}
