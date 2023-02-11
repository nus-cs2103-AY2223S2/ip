package formatters;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Utilities class to process Dates and Time.
 */
public class DateTimeUtils {

    /**
     * Returns true if date is of the correct format.
     * @param date string date
     * @return true if date is of correct format
     */
    public static boolean isCorrectDateFormat(String date) {
        try {
            System.out.println(date);
            LocalDate localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if time is of the correct format.
     * @param time string time
     * @return true if time is of correct format
     */
    public static boolean isCorrectTimeFormat(String time) {
        try {
            LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("Hmm"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
