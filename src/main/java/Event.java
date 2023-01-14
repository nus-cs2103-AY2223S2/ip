/**
 * Event is a Task that starts at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    protected String from, to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
