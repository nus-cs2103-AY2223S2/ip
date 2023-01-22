package formatters;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtils {
    public static boolean isCorrectDateFormat(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    public static boolean isCorrectTimeFormat(String time) {
        try {
            LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("Hmm"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
