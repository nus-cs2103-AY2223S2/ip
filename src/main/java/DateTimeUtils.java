import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeUtils {
    public static String dateFormatter(String date) throws DukeException {
        try {
            LocalDate localDate = LocalDate.parse(date);
            return localDate.format(DateTimeFormatter.ofPattern("dd MMM uuuu"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong Date Format, please write in yyyy-MM-dd");
        }
    }
}
