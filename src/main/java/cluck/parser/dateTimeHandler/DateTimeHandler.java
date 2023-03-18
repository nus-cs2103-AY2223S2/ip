package cluck.parser.dateTimeHandler;

import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * The type Date time handler is used to handle date and time input by the user.
 */
public class DateTimeHandler {
    private static final Pattern TODAY_PATTERN = Pattern.compile("today", Pattern.CASE_INSENSITIVE);
    private static final Pattern TOMORROW_PATTERN = Pattern.compile("tomorrow", Pattern.CASE_INSENSITIVE);
    private static final Pattern WEEK_PATTERN = Pattern.compile("week", Pattern.CASE_INSENSITIVE);
    private static final Pattern NEXT_PATTERN = Pattern.compile("next", Pattern.CASE_INSENSITIVE);
    private static final Pattern MONTH_PATTERN = Pattern.compile("month", Pattern.CASE_INSENSITIVE);
    private static final DateTimeFormatter DAY_OF_WEEK_Formatter = DateTimeFormatter.ofPattern("E");

    protected static final DateTimeFormatter FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("dd [MM][MMM][MMMM] [yyyy][yy] HH[mm]")
            .toFormatter(Locale.ROOT);


//    private LocalDateTime makeSenseOfDateTimeString(String dateTimeString) {
//
//    }


}
