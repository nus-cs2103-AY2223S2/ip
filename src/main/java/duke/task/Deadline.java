package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Creates the Deadline class.
 */
public class Deadline extends Task {
    /**
     * Consistent serialVersionUID value
     */
    private static final long serialVersionUID = 8394134908161266299L;
    /** Date of deadline */
    private final LocalDateTime deadlineDateTime;

    /**
     * Constructs a Deadline.
     *
     * @param description Description of task.
     * @param deadlineDateTime Deadline of task.
     */
    public Deadline(String description, LocalDateTime deadlineDateTime) {
        super(description);
        this.deadlineDateTime = deadlineDateTime;
    }

    /**
     * Gets deadline of task.
     *
     * @return Deadline of task.
     */
    public LocalDateTime getDeadline() {
        return deadlineDateTime;
    }

    /**
     * {@inheritDoc}
     *
     * Includes type of task and its deadline.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String deadline = this.deadlineDateTime.format(formatter);
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
