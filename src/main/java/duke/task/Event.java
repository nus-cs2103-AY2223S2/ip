package task;

/**
 * Represents an Event
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * Constructor to initialize an event object
     *
     * @param start The start date/time of the event
     * @param end The end date/time of the event
     * @param desc The title of the event
     */
    public Event(String start, String end, String desc) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the event
     *
     * @return The string representation of the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start + "to: " + end + ")";
    }
}
