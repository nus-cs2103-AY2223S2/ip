package elise;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class DateParser {
    private enum Day {
        MON, TUE, WED, THU, FRI, SAT, SUN
    }

    private static HashMap<Day, Integer> days = new HashMap<>();
    static {
        int num = 1;
        for (Day day : Day.values()) {
            days.put(day, num);
        }
    }

    private static DateTimeFormatter[] dateTimeFormats = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("dd-MM-uuuu HHmm"),
            DateTimeFormatter.ofPattern("dd/MM/uuuu HHmm"),
            DateTimeFormatter.ofPattern("dd MM uuuu")
    };

    private static DateTimeFormatter[] dateFormats = new DateTimeFormatter[] {
            DateTimeFormatter.ofPattern("dd MM uuuu"),
            DateTimeFormatter.ofPattern("dd-MM-uuuu"),
            DateTimeFormatter.ofPattern("dd/MM/uuuu"),
    };

    protected static MaybeDate parseDate(String s) {
        for (DateTimeFormatter format : dateTimeFormats) {
            try {
                return new MaybeDate(LocalDateTime.parse(s, format));
            } catch (DateTimeParseException e) {
                // do nothing try again.
            }
        }
        for (DateTimeFormatter format : dateFormats) {
            try {
                return new MaybeDate(LocalDate.parse(s, format).atTime(LocalTime.MAX));
            } catch (DateTimeParseException e) {
                // do nothing try again.
            }
        }
        String temp = s.toUpperCase();
        try {
            Day curr = Day.valueOf(temp);
            int day = days.get(curr);
            LocalDate now = LocalDate.now();
            int daysToAdd = (day - now.getDayOfWeek().getValue() + 7) % 7;
            return new MaybeDate(now.plusDays(daysToAdd).atTime(LocalTime.MAX));
        } catch (IllegalArgumentException e) {
            // not in enum value, continue.
        }
        return new MaybeDate(s);
    }
}
