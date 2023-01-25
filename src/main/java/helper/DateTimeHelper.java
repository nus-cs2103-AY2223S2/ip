package helper;

import exception.InvalidDateFormatException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class DateTimeHelper {

    public static LocalDateTime parse(String dateTimeString) throws InvalidDateFormatException {
        // Check if string is in the correct format.
        if (!dateTimeString.matches("\\d{1,3}/\\d{1,3}/\\d+ \\d{4}")) {
            throw new InvalidDateFormatException("Incorrect date format!");
        }

        // Parse day, month, year, hour and minute from dateTimeString.
        String[] values = dateTimeString.split("/");
        Integer day = Integer.valueOf(values[0]);
        Integer month = Integer.valueOf(values[1]);

        String[] yearAndTime = values[2].split(" ");
        Integer year = Integer.valueOf(yearAndTime[0]);
        Integer hour = Integer.valueOf(yearAndTime[1].substring(0, 2));
        Integer minute = Integer.valueOf(yearAndTime[1].substring(2));

        // Convert the time units into a LocalDateTime object.
        try {
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (DateTimeException e) {
            throw new InvalidDateFormatException("Incorrect datetime values!");
        }
    }

    public static LocalDateTime parseFormattedDateTime(String dateTimeString) throws InvalidDateFormatException {
        // Check if string is in the correct format.
        if (!dateTimeString.matches(".{3} \\d{1,3} \\d{4} \\d{2}:\\d{2}")) {
            throw new InvalidDateFormatException("Incorrect date format!");
        }

        // Parse day, month, year, hour and minute from dateTimeString.
        String[] values = dateTimeString.split(" ");
        Integer day = Integer.valueOf(values[1]);
        Integer month = convertMonth(values[0]);
        Integer year = Integer.valueOf(values[2]);

        String[] hoursAndMinutes = values[3].split(":");
        Integer hour = Integer.valueOf(hoursAndMinutes[0]);
        Integer minute = Integer.valueOf(hoursAndMinutes[1]);

        // Convert the time units into a LocalDateTime object.
        try {
            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (DateTimeException e) {
            throw new InvalidDateFormatException("Incorrect datetime values!");
        }
    }

    public static Integer convertMonth(String month) throws InvalidDateFormatException {
        // Generate HashMap to map months to their numerical values.
        HashMap<String, Integer> monthToNumber = new HashMap<>();

        monthToNumber.put("Jan", 1);
        monthToNumber.put("Feb", 2);
        monthToNumber.put("Mar", 3);
        monthToNumber.put("Apr", 4);
        monthToNumber.put("May", 5);
        monthToNumber.put("Jun", 6);
        monthToNumber.put("Jul", 7);
        monthToNumber.put("Aug", 8);
        monthToNumber.put("Sep", 9);
        monthToNumber.put("Oct", 10);
        monthToNumber.put("Nov", 11);
        monthToNumber.put("Dec", 12);

        Integer monthValue = monthToNumber.get(month);

        if (monthValue == null) {
            throw new InvalidDateFormatException("Incorrect month value!");
        }

        return monthValue;
    }

    public static String stringify(LocalDateTime dt) {
        return DateTimeFormatter.ofPattern("MMM d yyyy HH:mm").format(dt);
    }
}
