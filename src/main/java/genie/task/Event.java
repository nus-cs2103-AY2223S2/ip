package genie.task;

/**
 * Deals with creating event tasks.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * A constructor that takes in task descriptor and event duration
     * @param description of event
     * @param from start of event
     * @param to end of event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns event as a formatted string to print for list command.
     * @return event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from +
                " to: " + this.to + ")";
    }

    /**
     * Returns to do as a formatted string to print after loading .txt file.
     * @return event
     */
    @Override
    public String toFileFormat() {
        return "[E]" + super.toFileFormat() + " | " + this.from + " - " + this.to;
    }
}
