package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

/**
 * Contains functionality for parsing date and time input, as well as formatting
 * LocalDate and LocalDateTime objects.
 */
public class DateTimeParser {

    private static DateTimeFormatter parser = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendOptional(DateTimeFormatter.ofPattern("d-M-yyyy H:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("d-M-yyyy h:mma"))
            .appendOptional(DateTimeFormatter.ofPattern("d-M-yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy H:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy h:mma"))
            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d MMM yyyy H:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("d MMM yyyy h:mma"))
            .appendOptional(DateTimeFormatter.ofPattern("d MMM yyyy"))
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0).toFormatter();

    private static DateTimeFormatter dateStorageFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static DateTimeFormatter dateTimeStorageFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private static DateTimeFormatter dateDisplayFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");


    /**
     * Returns a LocalDateTime object from the given string containing date and time.
     * The format for the date and time string is "dd-mm-yyyy hh:ss". The time may be omitted.
     *
     * @param dateTimeString a date and time string with the format "dd-mm-yyyy hh:ss"
     * @return a LocalDateTime object containing the given date and time
     * @throws DateTimeParseException if dateTimeString does not have an accepted format
     */
    public static LocalDateTime parse(String dateTimeString) throws DateTimeParseException {
        return LocalDateTime.from(parser.parse(dateTimeString));
    }

    /**
     * Returns a String version of the specified date in a format suitable for storage.
     *
     * @param date a LocalDate object containing the date to be converted into a string.
     * @return String version of the specified date
     * @throws DateTimeException
     */
    public static String dateToStorageString(LocalDate date) throws DateTimeException {
        return dateStorageFormatter.format(date);
    }

    /**
     * Returns a String version of the specified date and time in a format suitable for storage.
     *
     * @param dateTime a LocalDateTime object containing the date and time to be converted into a string.
     * @return String version of the specified date and time
     * @throws DateTimeException
     */
    public static String dateTimeToStorageString(LocalDateTime dateTime) throws DateTimeException {
        return dateTimeStorageFormatter.format(dateTime);
    }

    /**
     * Returns a String version of the specified date in a format suitable for display.
     *
     * @param date a LocalDate object containing the date to be converted into a string.
     * @return String version of the specified date
     * @throws DateTimeException
     */
    public static String dateToDisplayString(LocalDate date) throws DateTimeException {
        String formattedDate = dateDisplayFormatter.format(date);
        int dayOfTheMonth = date.getDayOfMonth();
        String ordinal = getOrdinal(dayOfTheMonth);
        return dayOfTheMonth + ordinal + " " + formattedDate;
    }

    /**
     * Returns a String version of the specified date and time in a format suitable for display.
     *
     * @param dateTime a LocalDateTime object containing the date to be converted into a string.
     * @return String version of the specified date and time
     * @throws DateTimeException
     */
    public static String dateTimeToDisplayString(LocalDateTime dateTime) throws DateTimeException {
        String formattedDate = dateDisplayFormatter.format(dateTime);

        int dayOfTheMonth = dateTime.getDayOfMonth();
        String ordinal = getOrdinal(dayOfTheMonth);

        int minuteOfHour = dateTime.getMinute();
        String minutes = (minuteOfHour == 0) ? "" : (":" + minuteOfHour);

        int hourWithAmPm = dateTime.get(ChronoField.CLOCK_HOUR_OF_AMPM);
        String amPm = getAmPm(dateTime.getHour());

        return dayOfTheMonth + ordinal + " " + formattedDate + ", " + hourWithAmPm + minutes + amPm;
    }

    private static String getOrdinal(final int day) {
        assert day > 0 && day < 32;

        if (day >= 11 && day <= 13) {
            return "th";
        }
        switch (day % 10) {
        case 1: return "st";
        case 2: return "nd";
        case 3: return "rd";
        default: return "th";
        }
    }

    private static String getAmPm(final int hour) {
        assert hour >= 0 && hour <= 24;
        if (hour < 12) {
            return "am";
        } else {
            return "pm";
        }
    }
}
