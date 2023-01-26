package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Stream;

public class Parser {

    public Parser() {
        // Empty
    }

    // parse format is "YYYY-MM-DD"
    // TODO: need to catch exceptions
    //fixed to "-" seperator
    private LocalDate parseDate(String dateStr) {
        return LocalDate.parse(dateStr);
    }

    // parse format is "YYYY-MM-DD", and can also specify seperator
    // TODO: need to catch exceptions
    private LocalDate parseDate(String dateStr, char seperator) {
        String[] date = dateStr.split(String.valueOf(seperator));
        int[] dateInfo = Stream.of(date).mapToInt(Integer::parseInt).toArray();
        return LocalDate.of(dateInfo[0], dateInfo[1], dateInfo[2]);
    }


    // Can parse "HH:MM:SS" or "HH:MM"
    // TODO: need to catch exceptions
    private LocalTime parseTime(String timeStr) {
        return LocalTime.parse(timeStr);
    }

    // Can parse "HH:MM:SS" or "HH:MM", and can also specify seperator
    // TODO: need to catch exceptions
    private LocalTime parseTime(String timeStr, char seperator) {
        String[] time = timeStr.split(String.valueOf(seperator));
        int[] timeInfo = Stream.of(time).mapToInt(Integer::parseInt).toArray();
        if (timeInfo.length == 2)
            return LocalTime.of(timeInfo[0], timeInfo[1]);
        else
            return LocalTime.of(timeInfo[0], timeInfo[1], timeInfo[2]);
    }

    // Can parse "YYYY-MM-DD HH:MM:SS" or "YYYY-MM-DD HH:MM"
    public LocalDateTime parseDateTime(String str) {
        return parseDateTime(str, ' ');
    }

    public LocalDateTime parseDateTime(String str, char seperator) {
        String[] s = str.split(String.valueOf(seperator));
        return LocalDateTime.of(parseDate(s[0]), parseTime(s[1]));
    }

}
