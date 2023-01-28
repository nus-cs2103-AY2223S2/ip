package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class inherits from the Task class and represents a deadline task.
 */
public class Deadline extends Task {
    /** Deadline of the task */
    protected LocalDateTime by;

    /**
     * Initializes the deadline task.
     *
     * @param description Description of the deadline task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        String by = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
