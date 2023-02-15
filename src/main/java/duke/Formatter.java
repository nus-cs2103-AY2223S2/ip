package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the related fields and behavior of a Formatter that formats given inputs.
 */
public class Formatter {
    /**
     * Formats LocalDate to day month year format
     * and returns the string representation of it.
     *
     * @param localDate The given LocalDate to be formatted.
     * @return Returns the string representation of the formatted date.
     */
    public static String formatDateForPrint(LocalDate localDate) {
        int day = localDate.getDayOfMonth();
        String month = localDate.getMonth().toString();
        int year = localDate.getYear();
        return String.format("%d %s %d", day, month, year);
    }

    /**
     * Formats LocalDate to yyyy/mm/dd format
     * and returns the string representation of it.
     *
     * @param localDate The given LocalDate to be formatted.
     * @return Returns the string representation of the formatted date.
     */
    public static String formatDateForStorage(LocalDate localDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return localDate.format(dtf);
    }
}
