package duke.utils;

import java.time.format.DateTimeFormatter;

/**
 * Contains constants for LocalDateTime.
 */
public abstract class LocalDateTimeUtils {
    /** The format for any LocalDateTime inputted as a string. */
    public static final String INPUT_DATE_TIME_FORMAT = "dd/MM/yyyy HHmm";

    /**
     * The regex for checking if any LocalDateTime inputted as a string complies with the format specified in
     * {@link #INPUT_DATE_TIME_FORMAT}.
     */
    public static final String INPUT_DATE_TIME_REGEX = "[0-9]{2}/[0-9]{2}/[0-9]{4} [0-9]{2}[0-9]{2}";

    /** The formatter for the format specified in {@link #INPUT_DATE_TIME_FORMAT}. */
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            INPUT_DATE_TIME_FORMAT);

    /** The formatter for any LocalDateTime to be outputted. */
    public static final DateTimeFormatter OUTPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(
            "EEE MMM dd, yyyy hh:mm a");
}
