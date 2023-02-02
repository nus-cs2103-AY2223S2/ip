package duke.util;

import java.time.format.DateTimeFormatter;

/**
 * Utility class containing date time formats.
 */
public class DateTimeUtils {
    public static final DateTimeFormatter DATE_TIME_FORMAT_INPUT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static final DateTimeFormatter DATE_TIME_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
}
