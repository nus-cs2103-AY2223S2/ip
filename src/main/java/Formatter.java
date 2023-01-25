import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulation of a Formatter that formats given inputs.
 */
public class Formatter {
    /**
     * Formats LocalDate to day month year format and returns the string representation of it.
     * @param ld The given LocalDate to be formatted.
     * @return Returns the string representation of the formatted date.
     */
    public static String formatDatePrint(LocalDate ld) {
        int day = ld.getDayOfMonth();
        String month = ld.getMonth().toString();
        int year = ld.getYear();
        return String.format("%d %s %d",day, month, year);
    }

    /**
     * Formats LocalDate to yyyy/mm/dd format and returns the string representation of it.
     * @param ld The given LocalDate to be formatted.
     * @return Returns the string representation of the formatted date.
     */
    public static String formatDateStore(LocalDate ld) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return ld.format(dtf);
    }
}
