package duke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

/**
 * Represents a task that starts at a specific time.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Initialises the task with the task name, start and end of task.
     *
     * @param description Name of the task.
     * @param from Start date/time of occurrence of the task.
     * @param to End date/time of occurrence of the task.
     * @throws DukeException If the start and end of task is in invalid.
     */
    public Event(String description, String from, String to) throws DukeException {
        super(description);

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.from = LocalDateTime.parse(from, formatter);
            this.to = LocalDateTime.parse(to, formatter);
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
        return "E$$$" + super.toSaveString() + "$$$"
                + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "$$$"
                + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Returns a string representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
