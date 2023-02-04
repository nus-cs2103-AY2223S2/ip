package date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DukeDate {
    private static final DateTimeFormatter INPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    public static LocalDate parseDateString(String dateString) {
        return LocalDate.parse(dateString, INPUT_DATE_FORMAT);
    }

    public static String convertDateToString(LocalDate date) {
        return date.format(OUTPUT_DATE_FORMAT);
    }
}
