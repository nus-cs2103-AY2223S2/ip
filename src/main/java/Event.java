/**
 * Represents an Event task. An Event task has a description,
 * a 'from' and 'to' timeframe, and can be marked as done.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean bool, String from, String to) {
        super(description, bool);
        this.from = from;
        this.to = to;
    }

    /**
     * Marks an Event task as done.
     * @return Event task marked as done.
     */
    @Override
    public Event markAsDone() {
        return new Event(description, true, from, to);
    }

    /**
     * Unmarks an Event task from being done.
     * @return Event task unmarked from being done.
     */
    @Override
    public Event unmarkAsDone() {
        return new Event(description, from, to);
    }

    /**
     * Returns data for storage purposes.
     * @return Data for storage purposes.
     */
    @Override
    public String getDataToSave() { return "E / " + getStatusNum() + " / " + getDesc() + " / " + from + " / " + to; }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}