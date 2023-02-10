package core;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Core.DateHandler handles all things related to date.
 * @author EL
 */

public class DateHandler {

    private static String dtfPattern = "yyyy/MM/dd HHmm";
    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(dtfPattern);

    private static String printPattern = "dd/MM/yyyy HH:mm";
    private static DateTimeFormatter printFormat = DateTimeFormatter.ofPattern(printPattern);

    /**
     * Converts string input into LocalDateTime.
     * By default, format is set by DTFPattern.
     *
     * @param userin User input
     * @return LocalDateTime object representing time.
     * @throws exceptions.invalid.Input Thrown when the user enters an incorrect date time.
     */
    public static LocalDateTime convert(String userin) throws exceptions.invalid.Input {
        try {
            return LocalDateTime.parse(userin, dateTimeFormat);
        } catch (DateTimeParseException e) {
            throw new exceptions.invalid.Input(
                    String.format("Incorrect date time format! Should be '%s'", dtfPattern));
        }
    }

    /**
     * Unconverts pattern dateTimeFormat input into DTFPattern.
     * Primarily used for saving.
     * @param userin LocalDateTime in dateTimeFormat
     * @return userin in DTFPattern DTFPattern
     */
    public static String unconvert(LocalDateTime userin) {
        return userin.format(DateTimeFormatter.ofPattern(dtfPattern));
    }

    /**
     * Return time now according to dateTimeFormat format.
     * @return the time now.
     */
    public static LocalDateTime timeNow() {
        LocalDateTime ret = LocalDateTime.now();
        ret.format(dateTimeFormat);
        return ret;
    }
}
