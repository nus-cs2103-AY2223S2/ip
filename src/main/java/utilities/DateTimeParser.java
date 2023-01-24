package utilities;

import exceptions.SaturdayException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class DateTimeParser {
    private static final String[] DATE_TIME_FORMATS = {
            "dd-MM-yyyy",
            "dd/MM/yyyy",
            "dd.MM.yyyy",
            "dd MM yyyy",
            "dd-MM-yyyy HH:mm",
            "dd/MM/yyyy HH:mm",
            "dd.MM.yyyy HH:mm",
            "dd MM yyyy HH:mm",
            "dd-MM-yyyy hh:mm a",
            "dd/MM/yyyy hh:mm a",
            "dd.MM.yyyy hh:mm a",
            "dd MM yyyy hh:mm a",
            "HH:mm dd-MM-yyyy",
            "HH:mm dd/MM/yyyy",
            "HH:mm dd.MM.yyyy",
            "HH:mm dd MM yyyy",
            "hh:mm a dd-MM-yyyy",
            "hh:mm a dd/MM/yyyy",
            "hh:mm a dd.MM.yyyy",
            "hh:mm a dd MM yyyy"
    };
    private static final DateTimeFormatter PRINT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

    public static TemporalAccessor parseDate(String date) {
        for (String format : DATE_TIME_FORMATS) {
            try {
                return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
            } catch (DateTimeParseException e) {
                try {
                    return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
                } catch (DateTimeParseException e1) {
                    // try next format
                }
            }
        }
        throw new SaturdayException("Invalid date format: " + date);
    }

    public static String printDateTime(TemporalAccessor dateTime) {
        String result;
        DateTimeFormatter formatter;
        if (dateTime instanceof LocalDate) {
            formatter = DateTimeFormatter.ofPattern("MMM dd yy");
            result = formatter.format(dateTime);
        } else {
            formatter = DateTimeFormatter.ofPattern("MMM dd yy hh:mm a");
            result = formatter.format(dateTime);
        }
        return result;
    }

}
