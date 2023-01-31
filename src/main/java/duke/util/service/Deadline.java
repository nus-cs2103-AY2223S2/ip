package duke.util.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.util.Task;

/**
 * A more specific implementation of {@code Task}
 * as specified by the user.
 */

public class Deadline extends Task {
    private LocalDateTime deadline;

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
        return super.toString() + " (BY: " + this.deadline.format(
                DateTimeFormatter.ofPattern("hh:mm a MMM dd yyyy")) + ")";
    }
}
