package task;

/**
 * A task with a starting and ending date and time.
 */
public class Event extends Task {

    /** Starting date and time of event */
    protected String from;

    /** Ending date and time of event */
    protected String to;

    /**
     * Constructs a new event.
     *
     * @param description The description of the event.
     * @param from The starting date and time of the event.
     * @param to The ending date and time of the event.
     */
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
