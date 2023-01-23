import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class DateTimeHelper {
    public static LocalDateTime parse(String dateTimeString) throws InvalidDateFormatException{
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

    public static LocalDateTime parseFormattedDateTime(String dateTimeString) throws InvalidDateFormatException{
        if (!dateTimeString.matches(".{3} \\d{1,3} \\d{4} \\d{2}:\\d{2}")) {
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

    public static String stringify(LocalDateTime dt) {
        return DateTimeFormatter.ofPattern("MMM d yyyy HH:mm").format(dt);
    }
}
