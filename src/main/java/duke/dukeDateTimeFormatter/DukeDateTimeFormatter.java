package dukeDateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class DukeDateTimeFormatter {
    static DateTimeFormatter formatTo = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mma");
    static DateTimeFormatter formatFrom = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static String format(String datetimeString) {
        try {
            LocalDateTime date = LocalDateTime.parse(datetimeString, formatFrom);
            return date.format(DukeDateTimeFormatter.formatTo);
        } catch (DateTimeParseException e) {
            System.out.println("Datetime has to be in the following format: yyyy-mm-dd HHmm");
        }

        return "";
    }

    public static boolean isValidDateTimeFormat(String datetimeString) {
        try {
            LocalDateTime.parse(datetimeString, formatFrom);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Datetime has to be in the following format: yyyy-mm-dd HHmm");
        }

        return false;
    }
}
