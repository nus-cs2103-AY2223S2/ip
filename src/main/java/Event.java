
/**
 * Contains information of an event
 * Contains description and duration of the event
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Creates an event object
     *
     * @param description The description of the event
     * @param from Starting time of the event
     * @param to Ending time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns type of task, completion status, description, and duration of
     * the event
     *
     * @return Type of task, completion status, description, and duration of
     * the event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from
                + " to: " + to + ")";
    }
}
