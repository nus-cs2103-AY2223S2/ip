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
    protected String startTime;
    protected String endTime;

    /**
     * Constructor of Event.
     *
     * @param description Description of the event.
     * @param startTime Start time of event.
     * @param endTime End time of event.
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns a string representation of Event.
     * @return representation String representation of Event
     */
    public String toString() {
        return "[E]" + getStatusIcon() + " " + description
                + " (from: "
                + startTime + " to: "
                + endTime + ")";
    }
}