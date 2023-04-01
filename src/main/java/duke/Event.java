package duke;

/**
 * Event Task which contains description, when the event begins and ends.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Creating an Event with a from and to parameter.
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
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toFileString() {
        return "E | " + super.toFileString() + " | " + this.from + " | " + this.to + "\n";
    }
}

