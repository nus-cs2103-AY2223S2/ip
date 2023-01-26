package core;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Core.DateHandler handles all things related to date.
 * @author EL
 */

public class DateHandler {

    private static String DTFPattern = "yyyy/MM/dd HHmm";
    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DTFPattern);

    private static String printPattern = "dd/MM/yyyy HH:mm";
    private static DateTimeFormatter printFormat = DateTimeFormatter.ofPattern(printPattern);

//    /**
//     * Sets pattern for DateTimeFormatter.
//     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html">
//     *     Java Dcoumentation on Patterns for Formatting and Parsing</a>
//     * @param pattern Pattern to set.
//     */
//    public static void setDTFPattern(String pattern) {
//        DateTimeFormatter input = null;
//        try {
//            input = DateTimeFormatter.ofPattern(pattern);
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException(e);
//        }
//        DTFPattern = pattern;
//        dateTimeFormat = DateTimeFormatter.ofPattern(DTFPattern);
//    }
//
//    public static void setPrintPattern(String pattern) {
//        DateTimeFormatter input = null;
//        try {
//            input = DateTimeFormatter.ofPattern(pattern);
//        } catch (IllegalArgumentException e) {
//            throw new IllegalArgumentException(e);
//        }
//        printPattern = pattern;
//        printFormat = DateTimeFormatter.ofPattern(printPattern);
//    }

    /**
     * Converts string input into LocalDateTime.
     * By default, format is set by DTFPattern.
     *
     * @param userin User input
     * @return LocalDateTime object representing time.
     * @throws exceptions.invalid.Input Thrown when the user enters an incorrect date time.
     */
    public static LocalDateTime convert(String userin) throws exceptions.invalid.Input {

        LocalDateTime LDT = null;
        try {
            LDT = LocalDateTime.parse(userin, dateTimeFormat);
        } catch (DateTimeParseException e) {
            throw new exceptions.invalid.Input(
                    String.format("Incorrect date time format! Should be '%s'", DTFPattern));
        }
        return LDT;
    }

    public static String unconvert(LocalDateTime userin) {
        return userin.format(DateTimeFormatter.ofPattern(DTFPattern));
    }

//    /**
//     * Returns String input formatted according to the stored format
//     * @param datetime The time to print
//     * @return String representation of Date Time according to the format
//     */
//    public static String print(LocalDateTime datetime) {
//        return print(datetime, printFormat);
//    }
//
//    /**
//     * Returns String input formatted according to the given format
//     * @param datetime The time to print
//     * @param format Custom DateTimeFormatter format
//     * @return String representation of Date Time according to the format
//     */
//    private static String print(LocalDateTime datetime, DateTimeFormatter format) {
//        return datetime.format(format);
//    }
}
