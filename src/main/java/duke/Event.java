package duke;

/**
 * Represents a Task that is an event.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates new Event object.
     *
     * @param description The description of the event.
     * @param from        The starting time of the event.
     * @param to          The ending time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Sets the new starting time of the event.
     *
     * @param newFrom The new starting time of the event.
     */
    public void setFrom(String newFrom) {
        this.from = newFrom;
    }

    /**
     * Sets the new ending time of the event.
     *
     * @param newTo The new ending time of the event.
     */
    public void setTo(String newTo) {
        this.to = newTo;
    }

    /**
     * Overrides the default Object::toString.
     *
     * @return String representation of an Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + description + "(from: " + from + "to: " + to + ")";
    }
}
