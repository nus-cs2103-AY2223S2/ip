package duke.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class DateUtil {

    //Solution below adapted from https://www.waitingforcode.com/java-8/managing-different-date-time-formats-datetimeformatterbuilder/read
    public static final DateTimeFormatter DATE_FORMATTER = new DateTimeFormatterBuilder()
        .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        .appendOptional(DateTimeFormatter.ISO_LOCAL_DATE)
        .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"))
        .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy"))
        .appendOptional(DateTimeFormatter.ofPattern("MMM d yyyy H:m"))
        .appendOptional(DateTimeFormatter.ofPattern("MMM d yyyy"))
        .toFormatter();

    //Solution below adapted from https://stackoverflow.com/a/48280447
    public static LocalDateTime toLocalDateTime(String input) throws DateTimeParseException {
        LocalDateTime dateTime;
        TemporalAccessor temporalAccessor = DATE_FORMATTER.parseBest(input, LocalDateTime::from, LocalDate::from);
        if (temporalAccessor instanceof LocalDateTime) {
            dateTime = (LocalDateTime) temporalAccessor;
        } else {
            dateTime = ((LocalDate) temporalAccessor).atStartOfDay();
        }
        return dateTime;
    }

    public static String dateToString(LocalDateTime date) {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }
}
