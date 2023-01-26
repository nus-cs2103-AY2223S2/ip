package duke.datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class to manage conversion date and time in Task between LocalDateTime and String
 */
public class DateTime {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public DateTime() {}

    /**
     * Converts string representing datetime into LocalDateTime
     *
     * @param dateTimeString String representing datetime of task
     * @return LocalDateTime of Task
     * @throws DateTimeParseException If the format of string datetime is wrong
     */
    public static LocalDateTime parseDateTimeString(String dateTimeString) throws DateTimeParseException {
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTime.formatter);
        return dateTime;
    }

    /**
     * Given the datetime of Task, convert it into string form
     *
     * @param dateTime LocalDateTime of Task
     * @return String version of Task datetime
     */
    public static String getDateTimeString(LocalDateTime dateTime) {
        return dateTime.format(DateTime.formatter);
    }
}
