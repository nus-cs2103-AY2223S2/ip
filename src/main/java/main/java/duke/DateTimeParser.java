package main.java.duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

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
            .appendOptional(DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH))
            .appendOptional(DateTimeFormatter.ofPattern("d MMMM yyyy H:mm", Locale.ENGLISH))
            .appendOptional(DateTimeFormatter.ofPattern("d MMMM yyyy h:mma", Locale.ENGLISH))
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0).toFormatter();

    private static DateTimeFormatter dateStorageFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private static DateTimeFormatter dateTimeStorageFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    private static DateTimeFormatter dateDisplayFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");



    public static LocalDateTime parse(String dateTimeString) throws DateTimeParseException {
        return LocalDateTime.from(parser.parse(dateTimeString));
    }

    public static String dateToStorageString(LocalDate date) throws DateTimeException {
        return dateStorageFormatter.format(date);
    }

    public static String dateTimeToStorageString(LocalDateTime dateTime) throws DateTimeException {
        return dateTimeStorageFormatter.format(dateTime);
    }

    public static String dateToDisplayString(LocalDate date) throws DateTimeException {
        String formattedDate = dateDisplayFormatter.format(date);
        int dayOfTheMonth = date.getDayOfMonth();
        String ordinal = getOrdinal(dayOfTheMonth);
        return dayOfTheMonth + ordinal + " " + formattedDate;
    }

    public static String dateTimeToDisplayString(LocalDateTime dateTime) throws DateTimeException {
        String formattedDate = dateDisplayFormatter.format(dateTime);

        int dayOfTheMonth = dateTime.getDayOfMonth();
        String ordinal = getOrdinal(dayOfTheMonth);

        int minuteOfHour = dateTime.getMinute();
        String minutes = (minuteOfHour == 0) ? "" : (":" + minuteOfHour);

        int hourWithAmPm = dateTime.get(ChronoField.CLOCK_HOUR_OF_AMPM);
        String amPm = getAmPm(dateTime.getHour());

        return dayOfTheMonth + ordinal + " " + formattedDate + ", " +  hourWithAmPm + minutes + amPm;
    }

    private static String getOrdinal(final int day) {
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
        if (hour < 12) {
            return "am";
        } else {
            return "pm";
        }
    }
}
