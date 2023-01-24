package duke.datetime;

import java.time.LocalDateTime;
import java.time.temporal.Temporal;

/** Represents a stub for DateTime in order to isolate dependencies during unit testing. */
public abstract class DateTimeStub {

    public static String formatDate(Temporal date) {
        if (date.equals(LocalDateTime.of(2023, 2, 2, 12, 30))) {
            return "February 02 2023 12:30";
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
