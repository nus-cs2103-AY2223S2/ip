package duke.task;

/**
 * Represents Event task with a description from when to when.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /**
     * Constructs an Event task.
     * @param description of the event
     * @param from start date or time of event
     * @param to end date or time of event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toTxt() {
        return String.format("E | %s | %s | %s", super.toTxt(), from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
