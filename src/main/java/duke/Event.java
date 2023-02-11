package duke;

/**
 * Represents a Task that is an event.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public void setFrom(String newFrom) {
        this.from = newFrom;
    }

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
