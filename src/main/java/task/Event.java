package task;

import smartduke.DukeException;
import smartduke.Parser;

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

    @Override
    public String getSavedFormat() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | "
                + Parser.dePrettifyDateTime(this.from) + " | " + Parser.dePrettifyDateTime(this.to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Parser.prettifyDateTime(this.from)
                + " to: " + Parser.prettifyDateTime(this.to) + ")";
    }
}
