package duke;

/**
 * Event class
 */
public class Event extends Task {

    String from;
    String to;

    /**
     * constructor for an instantiating an event object
     * @param description description of the event
     * @param from start time of the event
     * @param to end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event object
     * @return String string representation of the event object, which includes task type, completion status, from date
     * and to date
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";

    }
}
