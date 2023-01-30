package duke.util.service;

import duke.util.Task;

/**
 * A more specific implementation of {@code Task}
 * as specified by the user.
 */

public class Deadline extends Task {
    String deadline;

    /**
     * Construct a {@code Deadline} with the
     * deadline and action specified by the user
     * with the keyword "/BY".
     */
    public Deadline(String deadline, String action) {
        super("D", action);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (BY: " + this.deadline + ")";
    }
}