package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DateTimeHandler {
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
    private final String input;
    private final LocalDateTime parsedInput;

    DateTimeHandler(String input) {
        this.input = input;
        this.parsedInput = this.parseInput();
    }

    private LocalDateTime parseInputWithAcceptedPatterns(String[] acceptedPatterns) {
        TemporalAccessor t = null;
        DateTimeFormatter dtf;
        for (String pattern : acceptedPatterns) {
            dtf = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern(pattern)
                    .toFormatter();
            try {
                t = dtf.parse(input);
                break;
            } catch (DateTimeParseException e) {
                //Input does not fit the current format, loop will continue and try the next format
            }
        }
        if (t == null) {
            throw new DateTimeParseException("Unable to parse date!", input, 0);
        }

        LocalDateTime result = DEFAULT;
        try {
            for (ChronoField field : ChronoField.values()) {
                if (t.isSupported(field)) {
                    result = result.with(field, t.getLong(field));
                }
            }
        } catch (DateTimeException e) {
            //Reversing ChronoField.values() and iterating over them again is required to parse some edge cases
            //If day is set before month, the intermediate date may be invalid (eg: 30 feb)
            //although the final date is valid (eg: 30 mar). The invalid intermediate date throws an error
            //and the date cannot be parsed.
            List<ChronoField> l = Arrays.asList(ChronoField.values());
            Collections.reverse(l);
            for (ChronoField field : l) {
                if (t.isSupported(field)) {
                    result = result.with(field, t.getLong(field));
                }
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
     * @throws DateTimeParseException Thrown when parser is unable to format input string
     * @return LocalDateTime object corresponding to the input string
     */
    private LocalDateTime parseInput() throws DateTimeParseException {
        try {
            return parseInputWithAcceptedPatterns(acceptedDatePatterns);
        } catch (DateTimeParseException e) {
            //No error, try to parse as time first
        }
        try {
            return parseInputWithAcceptedPatterns(acceptedTimePatterns);
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
            return parseInputWithAcceptedPatterns(l.toArray(new String[0]));
        } catch (DateTimeParseException e) {
            throw new DateTimeParseException("Unable to parse input date time!", this.input, 0);
        }
    }

    /**
     * Formats the stored LocalDateTime object in dd MMM yyyy hh:mma format.
     * @return The String of the formatted LocalDateTime object.
     */
    public String formatPrint() {
        return parsedInput.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma"));
    }

    /**
     * Formats the stored LocalDateTime object in dd/MM/yyyy HHmm format. Used to save TaskList into a text file.
     * @return The String of the formatted LocalDateTime object.
     */
    public String formatSave() {
        return parsedInput.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }
}
