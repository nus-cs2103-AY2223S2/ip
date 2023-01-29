import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class DateHandler {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static String parse(String input) {
        try {
            LocalDate date = LocalDate.parse(input);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch(DateTimeParseException e) {
            return input;
        }
    }


}
