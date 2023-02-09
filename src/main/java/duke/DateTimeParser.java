package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.List;

public class DateTimeParser {
    private static final LocalDateTime DEFAULT = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
    private static final String[] acceptedDatePatterns = {
            "d/M/yyyy",
            "d/M/yy",
            "d/M",
            "d M yyyy",
            "d M yy",
            "d M",
            "d LLL",
            "d LLL yy",
            "d LLL yyyy",
            "d LLLL",
            "d LLLL yy",
            "d LLLL yyyy"
    };
    private static final String[] acceptedTimePatterns = {
            "HHmm",   //2359
            "h:m",    //1:30
            "h:m a",  //1:30 PM
            "h:ma",   //1:30PM
            "h a",    //12 PM
            "ha",     //12PM
            "h"       //12
    };

    private static LocalDateTime parseInputWithAcceptedPatterns(String inputDate, String[] acceptedPatterns) {
        TemporalAccessor t = null;
        DateTimeFormatter dtf = null;
        for (String pattern : acceptedPatterns) {
            dtf = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern(pattern)
                    .toFormatter();
            try {
                t = dtf.parse(inputDate);
                break;
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        if (t == null) {
            throw new DateTimeParseException("Unable to parse date!", inputDate, 0);
        }

        LocalDateTime result = DEFAULT;
        for (ChronoField field : ChronoField.values()) {
            if (t.isSupported(field)) {
                result = result.with(field, t.getLong(field));
            }
        }
        return result;
    }



    /**
     * Parses a given input string into a LocalDateTime object.
     * Supports many date and time formats.
     * Date and times entered are case-insensitive.
     *
     * Supported date formats are:
     *             "d/M/yyyy",
     *             "d/M/yy",
     *             "d/M",
     *             "d M yyyy",
     *             "d M yy",
     *             "d M",
     *             "d LLL",
     *             "d LLL yy",
     *             "d LLL yyyy",
     *             "d LLLL",
     *             "d LLLL yy",
     *             "d LLLL yyyy"
     *
     * Supported time formats are:
     *             "HHmm",   //2359
     *             "h:m",    //1:30
     *             "h:m a",  //1:30 PM
     *             "h:ma",   //1:30PM
     *             "h a",    //12 PM
     *             "ha",     //12PM
     *             "h"       //12
     *
     * Any combination of dateFormat + timeFormat, separated with a space, is also accepted.
     * Eg: 1 jan 2023 1:30pm
     * Combination of "d LLL yyyy" + "h:ma"
     *
     * @param dateTime String to be format into LocalDateTime object.
     * @throws DateTimeParseException Thrown when parser is unable to format input string
     * @return LocalDateTime object corresponding to the input string
     */
    public static LocalDateTime parseInput(String dateTime) throws DateTimeParseException {
        try {
            return parseInputWithAcceptedPatterns(dateTime, acceptedDatePatterns);
        } catch (DateTimeParseException e) {
            //No error, try to parse as time first
        }
        try {
            return parseInputWithAcceptedPatterns(dateTime, acceptedTimePatterns);
        } catch (DateTimeParseException e) {
            //No error, try to parse as DateTime first
        }
        try {
            List<String> l = new ArrayList<>();
            for (String datePattern : acceptedDatePatterns) {
                for (String timePattern : acceptedTimePatterns) {
                    l.add(datePattern + " " + timePattern);
                }
            }
            return parseInputWithAcceptedPatterns(dateTime, l.toArray(new String[0]));
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Unable to parse input date time!", dateTime, 0);
        }
    }

    /**
     * Formats a LocalDateTime object in dd MMM yyyy hh:mma format.
     * @param dt The LocalDateTime object to be formatted.
     * @return The String of the formatted LocalDateTime object.
     */
    public static String formatOutput(LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma"));
    }

    /**
     * Formats a LocalDateTime object in dd/MM/yyyy HHmm format. Used to save TaskList into a text file.
     * @param dt The LocalDateTime object to be formatted.
     * @return The String of the formatted LocalDateTime object.
     */
    public static String formatSave(LocalDateTime dt) {
        return dt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}
