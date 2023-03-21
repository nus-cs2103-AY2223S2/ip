package cbot.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Manages text-datetime translation. Reads text (into datetime), and writes datetime (into text).
 *
 * @see LocalDateTime
 */
public class TimeStuff {
    // checkstyle doesn't like the 8-space indentation here
    private static final String[] D_FORMS = {"y-M-d", "d/M/y", "d MMM y", "MMM d y"};
    private static final String[] T_FORMS = {" Hmm", " H:m", " ha", " h a", " h.ma", " h.m a", " h:ma", " h:m a"};
    private static final DateTimeFormatter UI_FORM = DateTimeFormatter.ofPattern("dd/MM/yy, HHmm");

    /**
     * Parses a given text into datetime. Has a set pool of accepted formats, including (but
     * not limited to) "yyyy-MM-DD HHmm", "d/M/y H:m", and "MMM d y".
     *
     * @param dtStr The text to be parsed.
     * @return The corresponding datetime.
     * @throws DateTimeParseException If dtStr is not in a recognized format.
     */
    public static LocalDateTime textToDT(String dtStr)
            throws DateTimeParseException {
        String str = dtStr.trim();

        for (String dForm : D_FORMS) {
            try {
                return tryAllForms(str, dForm);
            } catch (DateTimeParseException ignore) {
                // no handling, continue to next dForm
            }
        }

        return LocalDateTime.parse(str);
    }

    private static LocalDateTime tryAllForms(String str, String dForm)
            throws DateTimeParseException {
        for (String tForm : T_FORMS) {
            try {
                String toUpper = str.toUpperCase();
                String fullForm = dForm + tForm;
                return LocalDateTime.parse(toUpper, DateTimeFormatter.ofPattern(fullForm));
            } catch (DateTimeParseException ignore) {
                // no handling, continue to next tForm
            }
        }

        return LocalDate.parse(str, DateTimeFormatter.ofPattern(dForm)).atStartOfDay();
    }

    /**
     * Writes datetime in the "dd/MM/yy, HHmm" format.
     *
     * @param dt The datetime to be re-written.
     * @return A read-friendly form of datetime.
     */
    public static String dtToText(LocalDateTime dt) {
        return dt.format(UI_FORM);
    }
}
