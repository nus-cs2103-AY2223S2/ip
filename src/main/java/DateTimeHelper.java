import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static String stringify(LocalDateTime dt) {
        return DateTimeFormatter.ofPattern("MMM d yyyy HH:mm").format(dt);
    }
}
