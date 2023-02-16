package boo.datetime;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

/** Represents a stub for DateTime in order to isolate dependencies during unit testing. */
public abstract class DateTimeStub {

    /**
     * Returns a hard-coded date-time string.
     *
     * @param date A {@code Temporal} object representing 2023-02-02 12:30.
     * @return a formatted String representing the above date and time.
     */
    public static String formatDate(Temporal date) {
        if (date.equals(LocalDateTime.of(2023, 2, 2, 12, 30))) {
            return "February 02 2023 12:30";
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
