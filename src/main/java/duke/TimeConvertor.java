package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The TimeConvertor is to convert the user input time to a date type.
 */
public class TimeConvertor implements Comparable<TimeConvertor> {
    private final LocalDateTime realDate;

    /**
     * Converts to date type.
     *
     * @param timing User input time.
     */
    public TimeConvertor(String timing) {
        this.realDate = LocalDateTime.parse(timing, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Converts to date type.
     *
     * @param timing Time load from the record file.
     * @param type To indicate the time is loaded from a file instead of user input.
     */
    public TimeConvertor(String timing, String type) {
        this.realDate = LocalDateTime.parse(timing, DateTimeFormatter.ofPattern(type));
    }

    /**
     * Returns the date.
     *
     * @return The date in pattern yyyy-MM-dd.
     */
    public String getDate() {
        return realDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns the date.
     * With require to provide date type.
     *
     * @param type The pattern of date.
     * @return The date in given pattern.
     */
    public String getDate(String type) {
        return realDate.format(DateTimeFormatter.ofPattern(type));
    }

    /**
     * Displays the date.
     *
     * @return The date.
     */
    @Override
    public String toString() {
        return realDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
    }

    @Override
    public int compareTo(TimeConvertor o) {
        if (realDate.isBefore(o.realDate)) {
            return -1;
        } else if (realDate.isAfter(o.realDate)) {
            return 1;
        } else {
            return 0;
        }
    }
}
