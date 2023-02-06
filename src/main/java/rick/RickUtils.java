package rick;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The main utility class for providing Dates, DateTimes, and other common formatting.
 *
 * @author SeeuSim
 *         AY22/23-S2 CS2103T
 */
public class RickUtils {
    /**
     * A Utility method to parse datetime strings passed to the Rick.task.RickTask
     * subclass constructors.
     *
     * @param s The datetime string to be parsed.
     * @return The formatted datetime object.
     * @throws DateTimeParseException The error arising from an incorrectly
     *                                formatted string.
     */
    public static LocalDateTime parseDateTime(String s) throws DateTimeParseException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("d/M/yy HHmm");
        return LocalDateTime.parse(s, df);
    }

    /**
     * A Utility method to parse date strings passed to the rick.Rick command console.
     *
     * @param dte The date string to be parsed.
     * @return The formatted date object.
     * @throws DateTimeParseException The error arising from an incorrectly
     *                                formatted string.
     */
    public static LocalDate parseDate(String dte) throws DateTimeParseException {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("d/M/yy");
        return LocalDate.parse(dte, df);
    }
}
