package duke;

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of an Event instance with the "from" and "to" periods.
     * @return the desired string representation of an Event instance.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    /**
     * Returns the string representation of an Event instance with the "from" and "to" periods.
     * @return the desired string representation of an Event instance.
     */
    public String parse() {
        return "[E]" + super.parse() + " (from: " + from + " to: " + to + ")";
    }
}
