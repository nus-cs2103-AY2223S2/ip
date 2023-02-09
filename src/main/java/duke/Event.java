package duke;

/**
 * Represents a Task that is an event.
 */
public class Event extends Task {
    protected final String from;
    protected final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Overrides the default Object::toString.
     * @return String representation of an Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + description + "(from: " + from + "to: " + to + ")";
    }
}
