import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static String parseDate(String str) {
        LocalDateTime date = LocalDateTime.parse(str, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        String displayDate = date.format(dateFormat);
        return displayDate;
    }
}