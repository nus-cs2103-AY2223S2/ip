package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeChecker {

    public static String DEFAULT_TIME = "2359";
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

    /**
     * Updates the Date and Time given by the users.
     *
     * @param timeline Date and Time given by Users.
     * @return Updated Date and Time.
     */
    public static String updateTime(String timeline) {
        String[] split = timeline.split(" ");
        if (split.length == 2) {
            return timeline;
        } else {
            return timeline + " " + DEFAULT_TIME;
        }
    }
}
