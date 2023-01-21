/**
 * The Event class extends the Task class and represents an event with a start and end date.
 */
public class Event extends Task {
    private static final long serialVersionUID = 103;

    private String start;
    private String end;

    /**
     * Constructs an Event task with a start and end date.
     *
     * @param name  The name of the event.
     * @param start The date the event begins on.
     * @param end   The date the event ends on.
     */
    public Event(String name, String start, String end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
