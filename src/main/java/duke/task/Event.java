package duke.task;

/**
 * A subclass of Task that represents
 * a task that is considered an event.
 *
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor of Event.
     *
     * @param description Description of the event.
     * @param from Start time of event.
     * @param to End time of event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a string representation of Event.
     * @return representation String representation of Event
     */
    public String toString() {
        return "[E]" + getStatusIcon() + " " + description + " (from: " + from + " to: " + to + ")";
    }
}