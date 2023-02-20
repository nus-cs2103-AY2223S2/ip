package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline that has a due-date.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE, d LLL uuuu, hh:mm a");
    private LocalDateTime dueDate;

    /**
     * Creates a deadline object with the given task description and due-date.
     *
     * @param description The description of the task.
     * @param dueDate The due-date of the task.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return A string representation of the deadline,
     *     with the format "[D][X] Task description" if it is done,
     *     and "[D][ ] Task description" otherwise.
     */
    @Override
    public String toString() {

        return "[D]" + super.toString()
                + " (by: " + dueDate.format(FORMATTER) + ")";
    }

    @Override
    public String getTaskState() {
        return "D | " + super.getTaskString()
                + " | " + dueDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }
}
