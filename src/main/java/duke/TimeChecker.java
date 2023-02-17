package duke;

import java.time.format.DateTimeFormatter;


/**
 * Checks and Updates the Time accordingly.
 */
public class TimeChecker {

    /**
     * Default time to be provided in case users do not specify time.
     */
    private static String DEFAULT_TIME = "2359";

    /**
     * Time Formatter.
     */
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
