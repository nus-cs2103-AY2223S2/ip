package duke.util.service;

import duke.util.Task;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * A more specific implementation of {@code Task}
 * as specified by the user.
 */

public class Deadline extends Task {
    LocalDateTime deadline;

    /**
     * Construct a {@code Deadline} with the
     * deadline and action specified by the user
     * with the keyword "/BY".
     */
    public Deadline(LocalDateTime deadline, String action) {
        super("D", action);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (BY: " + this.deadline.format(DateTimeFormatter.ofPattern("hh:mm a MMM dd yyyy")) + ")";
    }
}