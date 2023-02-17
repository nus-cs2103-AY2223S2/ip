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
     * This method parses string input into LocalDateTime.
     * By default, format is set by DTFPattern.
     *
     * @param input User input
     * @return LocalDateTime object representing time.
     * @throws exceptions.invalid.Input Thrown when the user enters an incorrect date time.
     */
    public static LocalDateTime parse(String input) throws exceptions.invalid.Input {
        try {
            LocalDateTime ret = LocalDateTime.parse(input, dateTimeFormat);
            ret.format(printFormat);

            return ret;
        } catch (DateTimeParseException e) {
            throw new exceptions.invalid.Input(
                    String.format("Incorrect date time format! Should be '%s'", dtfPattern));
        }
    }

    /**
     * This method converts local date time into string format recognized by the load function.
     * Primarily used for saving.
     *
     * @param input LocalDateTime in dateTimeFormat
     * @return input in DTFPattern DTFPattern
     */
    public static String convertForSave(LocalDateTime input) {
        String ret = input.format(DateTimeFormatter.ofPattern(dtfPattern));
        return ret;
    }

    /**
     * This method is for printing of the time saved in Task.
     * Primarily used in print statements.
     *
     * @param input LocalDateTime in dateTimeFormat
     * @return input in DTFPattern DTFPattern
     */
    public static String convertForPrint(LocalDateTime input) {
        String ret = input.format(printFormat);
        return ret;
    }

}
