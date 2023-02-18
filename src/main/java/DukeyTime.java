import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DukeyTime {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate getDateFromString(String s) throws DukeyException {
        LocalDate date = LocalDate.now();
        try {
            date = LocalDate.parse(s, formatter);
        } catch (DateTimeException e) {
            throw new DukeyException("Error! Invalid date!");
        }
        return date;
    }

    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }
}
