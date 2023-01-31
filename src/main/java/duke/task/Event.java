package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructor for event task.
     *
     * @param description Description of the event task.
     * @param from Date when event starts.
     * @param to Date when even ends.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the task.
     * @return Representation of the task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString()
                + "(from " + this.from.format(formatter)
                + " to " + this.to.format(formatter) + ")";
    }

    /**
     * Returns the string representation of the task.
     * @return Representation of the task.
     */
    @Override
    public String toSavedString() {
        return "E" + "|" + (super.isDone ? "1" : "0")
                + "|" + super.description + "|" + this.from + "|" + this.to;
    }
}
