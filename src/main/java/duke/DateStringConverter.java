package duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class DateStringConverter {
    private final String[] DAY_OF_THE_WEEK = new String[]{"MONDAY", "TUESDAY", "WEDNESDAY",
            "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};

    public LocalDate convertDateInput(String input) {
        input.replace("/", "-");
        for (int i = 0; i < DAY_OF_THE_WEEK.length; i++) {
            if (input.equalsIgnoreCase(DAY_OF_THE_WEEK[i])) {
                DayOfWeek dayOfWeek = DayOfWeek.valueOf(input.toUpperCase());
                LocalDate today = LocalDate.now();
                LocalDate nextDate = today.with(dayOfWeek);
                return nextDate;
            }
        }
        return LocalDate.parse(input);
    }

    public LocalTime convertTimeInput(String input) {
        String hour = input.substring(0, 2);
        String mins = input.substring(2, 4);
        LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(mins));
        return time;
    }
}
