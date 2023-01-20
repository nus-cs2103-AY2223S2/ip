package duke.utils;

import java.time.format.DateTimeFormatter;

/**
 * Contains utility methods for LocalDateTime.
 */
public abstract class LocalDateTimeUtils {
    /**
     * The format for any LocalDateTime inputted as a string.
     */
    public static final String inputDateTimeFormat = "dd/MM/yyyy HHmm";
    /**
     * The regex for checking if any LocalDateTime inputted as a string complies with the format specified in
     * {@link #inputDateTimeFormat}.
     */
    public static final String inputDateTimeRegex = "[0-9]{2}/[0-9]{2}/[0-9]{4} [0-9]{2}[0-9]{2}";
    /**
     * The formatter for the format specified in {@link #inputDateTimeFormat}.
     */
    public static final DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern(inputDateTimeFormat);
    /**
     * The formatter for any LocalDateTime to be outputted.
     */
    public static final DateTimeFormatter outputDateTimeFormatter = DateTimeFormatter.ofPattern(
            "EEE MMM dd, yyyy hh:mm a");
}
