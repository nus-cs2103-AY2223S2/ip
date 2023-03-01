package duke.task;

/**
 * The type Event.
 */
public class Event extends Task {

    private String to;
    private String from;

    /**
     * Instantiates a new Event.
     *
     * @param description the description
     * @param from        the from
     * @param to          the to
     */
    public Event(String description, String from, String to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        return "[E]" + this.getStatusIcon() + " "
                + this.description + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSaveableString() {
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description, from, to);
    }
}
