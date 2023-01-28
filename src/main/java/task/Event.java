package task;

import duke.DukeException;
import duke.Parser;

import java.time.LocalDateTime;

/**
 * Event is a Task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for "Event" type task.
     * @param description The task description.
     * @param from The start date/time of the task.
     * @param to The end date/time of the task.
     * @throws DukeException If there is no description or start or end date/time indicated.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the formatted string representing this task to be saved to the local file.
     * @return The formatted string.
     */
    @Override
    public String getSavedFormat() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | "
                + Parser.dePrettifyDateTime(this.from) + " | " + Parser.dePrettifyDateTime(this.to);
    }

    /**
     * Gets the string representation of this task to be displayed on the Ui.
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.prettifyDateTime(this.from)
                + " to: " + Parser.prettifyDateTime(this.to) + ")";
    }
}
