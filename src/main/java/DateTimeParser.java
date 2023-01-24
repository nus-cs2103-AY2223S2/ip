import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {
    // Format deadline into a Datetime object
    // Format expected is 08/12/2023 1800
    public static LocalDateTime dateTimeParser(String input) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(input, format);
    }

    public static String datetimeFormatter(LocalDateTime datetime) {
        return datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
    }
}
