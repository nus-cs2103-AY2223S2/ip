package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task class.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for Deadline task.
     * @param description Description of task.
     * @param by Date task is due.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns due date of task.
     * @return due date of task.
     */
    public String getBy() {
        return this.by.toString();
    }

    /**
     * Returns string representation of deadline task.
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }
}
