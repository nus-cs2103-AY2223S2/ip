package duke.task;

import duke.DukeException;

/**
 * Represents an Event task.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor for a Event task.
     *
     * @param description of the task.
     * @param from the starting time of the event.
     * @param to the ending time of the event.
     * @throws DukeException if user inputs do not match expected format.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ") \n"
                + this.priority;
    }
}
