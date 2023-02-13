package duke;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The class to convert date given in string to Date class
 */
public class DateStringConverter {
    private final String[] dayOfWeek = new String[]{"FRIDAY", "MONDAY", "SATURDAY",
        "SUNDAY", "THURSDAY", "TUESDAY", "WEDNESDAY"};

    /**
     * A method to convert the String type to date type
     *
     * @param input a String input of date
     * @return A LocalDate object
     */
    public LocalDate convertDateInput(String input) {
        input.replace("/", "-");
        for (int i = 0; i < dayOfWeek.length; i++) {
            if (input.equalsIgnoreCase(dayOfWeek[i])) {
                DayOfWeek dayOfWeek = DayOfWeek.valueOf(input.toUpperCase());
                LocalDate today = LocalDate.now();
                LocalDate nextDate = today.with(dayOfWeek);
                return nextDate;
            }
        }
        return LocalDate.parse(input);
    }

    /**
     * A method to convert a String type to a LocalTime type
     *
     * @param input a String input of time
     * @return a LocalTime object converted from String type
     */
    public LocalTime convertTimeInput(String input) {
        String hour = input.substring(0, 2);
        String mins = input.substring(2, 4);
        LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(mins));
        return time;
    }
}
