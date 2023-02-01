package duke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

/**
 * Represents a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Initialises the task with the task name and deadline.
     *
     * @param description Name of the task.
     * @param by Deadline of the task.
     * @throws DukeException If the deadline is in invalid.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.by = LocalDateTime.parse(by, formatter);
        } catch (DateTimeException e) {
            throw new DukeException("Invalid date/time!");
        }
    }

    /**
     * Generates a string to save the task information on the storage file.
     *
     * @return String representation of the task for the storage file.
     */
    @Override
    public String toSaveString() {
        return "D$$$" + super.toSaveString() + "$$$"
                + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
