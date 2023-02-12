package duke.task;

import duke.DukeException;

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
                + " (from " + this.from.format(formatter)
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

    @Override
    public void setComponent(TaskComponent component, Object detail) throws DukeException {
        switch (component) {
        case DESCRIPTION:
            this.description = (String) detail;
            break;
        case FROM:
            this.from = (LocalDate) detail;
            break;
        case TO:
            this.to = (LocalDate) detail;
            break;
        default:
            throw new DukeException("EVENT task does not have this component: " + component);
        }
    }
}
