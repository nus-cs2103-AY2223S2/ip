/**
 * Represents an Event task
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creates an Event task object
     * @param description Describes the task
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from =  from;
        this.to = to;
    }

    /**
     * String representation of event task
     * @return String representation of event task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
