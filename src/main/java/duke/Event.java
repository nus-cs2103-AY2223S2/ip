package duke;

/**
 * Class that can store an Event task, which has additional "from" and "to" parameters.
 */
public class Event extends Task {
    protected Date from;
    protected Date to;

    /**
     * Constructor for an Event class.
     *
     * @param description Task description
     * @param from Task start time
     * @param to Task end time
     */
    public Event(String description, Date from, Date to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a String representation of the task that is parsable by the Duke Storage parser.
     *
     * @return A String object that is parsable by the Duke Storage parser.
     */
    @Override
    public String getFileRepresentation() {
        return "event|" + super.getFileRepresentation()
                + "|" + this.from.asFileDate()
                + "|" + this.to.asFileDate();
    }

    /**
     * Returns a String representation of the task to be printed on the screen.
     *
     * @return String representation for an Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from
                + " to: " + this.to + ")";
    }
}
