/**
 * A type of Task with a specific window for
 * completion.
 */
public class Event extends Task {
    // Start date for the task.
    protected String from;
    // End date for the task.
    protected String to;

    /**
     * Constructor for the event class.
     *
     * @param description Description of the event task.
     * @param from Start date for the task.
     * @param to End date for the task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Overrides the toString method of the Task class.
     *
     * @return String representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from + " to: " + to + ")";
    }
}
