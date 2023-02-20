package duke.util.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        super("DEADLINE", action);
        this.deadline = deadline;
    }
    /**
     * Construct a {@code Deadline} with the
     * deadline and action specified by the user
     * with the keyword "/BY".
     * A marked/ unmarked status of the task needs to be provided.
     */

    public Deadline(LocalDateTime deadline, String action, boolean isDone) {
        super("DEADLINE", action, isDone);
        this.deadline = deadline;
    }

    /**
     * Return the deadline in the form of a {@code String}
     */

    @Override
    public String getTimeInfo() {
        return "/BY " + deadline.format(
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    /**
     * Return the deadline date in the form of a list
     *
     * @return a list of {@code LocalDateTime} object consiting of
     *         the deadline date
     */

    public List<LocalDateTime> getDates() {
        return List.of(this.deadline);
    }

    @Override
    public String toString() {
        return super.toString() + " (BY: " + this.deadline.format(
                DateTimeFormatter.ofPattern("hh:mm a MMM dd yyyy")) + ")";
    }
}
