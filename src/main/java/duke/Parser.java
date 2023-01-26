package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Parser {

    public Parser() {
        // Empty
    }

    // parse format is "YYYY-MM-DD"
    // TODO: need to catch exceptions
    LocalDate parseDate(String dateStr) {
        //*
        return LocalDate.parse(dateStr); //fixed to "-" seperator
        /*/
        String[] date = dateStr.split("/"); // can also use "-" or other seperator
        int[] dateInfo = Stream.of(date).mapToInt(Integer::parseInt).toArray();
        return LocalDate.of(dateInfo[0], dateInfo[1], dateInfo[2]);
        /**/
    }

    // Can parse "HH:MM:SS" or "HH:MM"
    // TODO: need to catch exceptions
    LocalTime parseTime(String timeStr) {
        //*
        return LocalTime.parse(timeStr);
        /*/
        String[] time = timeStr.split(":");
        int[] timeInfo = Stream.of(time).mapToInt(Integer::parseInt).toArray();
        if (timeInfo.length == 2)
            return LocalTime.of(timeInfo[0], timeInfo[1]);
        else
            return LocalTime.of(timeInfo[0], timeInfo[1], timeInfo[2]);
        /**/
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
