package panav.task;

/**
 * Class to represent an Event. It contains attributes for its start and end timelines.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructor to initialise attributes.
     * @param description
     * @param from
     * @param to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
