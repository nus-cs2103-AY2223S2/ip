import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static LocalDate stringToDate(String duration) {
        LocalDate localDate = LocalDate.parse(duration);
        return localDate;
    }

    public static String dateToString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
