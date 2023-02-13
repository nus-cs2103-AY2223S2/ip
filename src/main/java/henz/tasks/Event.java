package henz.tasks;

/**
 * Event class extends the Task class.
 * @author Leng Wei Cong, Justin
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor.
     * @param description the description of the event task
     * @param from        the start datetime of the event task
     * @param to          the end datetime of the event task
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start datetime.
     * @return the start datetime
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Returns the end datetime.
     * @return the end datetime
     */
    public String getTo() {
        return this.to;
    }

    /**
     * Returns the string representation of the task.
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.from, this.to);
    }
}
