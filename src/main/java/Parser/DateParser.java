package Parser;

import DukeException.InvalidDateException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateParser {
    private final static String PARSE_DATE_FORMAT = "yyyy-MM-dd";
    private final static String PARSE_DATETIME_FORMAT = PARSE_DATE_FORMAT + " HH:mm";
    private final static String OUTPUT_DATE_FORMAT = "dd MMM yyyy";
    private final static String OUTPUT_DATETIME_FORMAT = OUTPUT_DATE_FORMAT + " @ HH:mm";
    private final static String STORAGE_DATETIME_FORMAT = PARSE_DATETIME_FORMAT;

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

    public static String formatDateToPrint(LocalDateTime datetime) {
        if (datetime.getHour() + datetime.getMinute() == 0) {
            return datetime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT));
        }
        return datetime.format(DateTimeFormatter.ofPattern(OUTPUT_DATETIME_FORMAT));
    }

    public static String formatDateToStore(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern(STORAGE_DATETIME_FORMAT));
    }
}
