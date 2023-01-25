package helper;

import exception.InvalidDateFormatException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * Represents the helper class to abstract LocalDateTime logic in Duke.
 */
public class DateTimeHelper {
    /**
     * Returns corresponding LocalDateTime object for the given string. This
     * method is used when receiving user input.
     * The string must be in the format DD/MM/YYYY HHMM where the year can
     * be any number of digits > 0 and each time-based unit (eg. day, month)
     * must be valid.
     *
     * @param dateTimeString A string representing the date.
     * @return The corresponding datetime object for the string.
     * @throws InvalidDateFormatException If the string does not follow the specified format.
     */
    public static LocalDateTime parse(String dateTimeString) throws InvalidDateFormatException {
        if (!dateTimeString.matches("\\d{1,3}/\\d{1,3}/\\d+ \\d{4}")) {
            throw new InvalidDateFormatException("Incorrect date format!");
        }

        // parse deadline string
        String[] values = dateTimeString.split("/");
        Integer day = Integer.valueOf(values[0]);
        Integer month = Integer.valueOf(values[1]);

        String[] yearAndTime = values[2].split(" ");
        Integer year = Integer.valueOf(yearAndTime[0]);
        Integer hour = Integer.valueOf(yearAndTime[1].substring(0, 2));
        Integer minute = Integer.valueOf(yearAndTime[1].substring(2));

        try {
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (DateTimeException e) {
            throw new InvalidDateFormatException("Incorrect datetime values!");
        }
    }

    /**
     * Returns corresponding LocalDateTime object for the given string. This
     * method is used when reading data from storage.
     * The string must be in the format MMM DD YYYY HH:MM where the year can
     * be any number of digits > 0 and each time-based unit (eg. day, month)
     * must be valid.
     *
     * @param dateTimeString A string representing the date.
     * @return The corresponding datetime object for the string.
     * @throws InvalidDateFormatException If the string does not follow the specified format.
     */
    public static LocalDateTime parseFormattedDateTime(String dateTimeString) throws InvalidDateFormatException{
        if (!dateTimeString.matches(".{3} \\d{1,3} \\d+ \\d{2}:\\d{2}")) {
            throw new InvalidDateFormatException("Incorrect date format!");
        }

        // parse deadline string
        String[] values = dateTimeString.split(" ");
        Integer day = Integer.valueOf(values[1]);
        Integer month = convertMonth(values[0]);
        Integer year = Integer.valueOf(values[2]);

        String[] hoursAndMinutes = values[3].split(":");
        Integer hour = Integer.valueOf(hoursAndMinutes[0]);
        Integer minute = Integer.valueOf(hoursAndMinutes[1]);

        try {
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (DateTimeException e) {
            throw new InvalidDateFormatException("Incorrect datetime values!");
        }
    }

    /**
     * Returns corresponding Integer for a given month expressed as an English word.
     *
     * @param month A string representing a month as an English word.
     * @return Integer value of the given month.
     * @throws InvalidDateFormatException If the string does not follow the specified format.
     */
    public static Integer convertMonth(String month) throws InvalidDateFormatException {
        HashMap<String, Integer> m = new HashMap<>();

        m.put("Jan", 1);
        m.put("Feb", 2);
        m.put("Mar", 3);
        m.put("Apr", 4);
        m.put("May", 5);
        m.put("Jun", 6);
        m.put("Jul", 7);
        m.put("Aug", 8);
        m.put("Sep", 9);
        m.put("Oct", 10);
        m.put("Nov", 11);
        m.put("Dec", 12);

        Integer monthValue = m.get(month);

        if (monthValue == null) {
            throw new InvalidDateFormatException("Incorrect month value!");
        }

        return monthValue;
    }

    /**
     * Convert a datetime object to a String in the format MMM d yyyy HH:mm.
     *
     * @param dt A datetime object to be converted in string format.
     * @return String representing the datetime value in the above format.
     */
    public static String stringify(LocalDateTime dt) {
        return DateTimeFormatter.ofPattern("MMM d yyyy HH:mm").format(dt);
    }
}
