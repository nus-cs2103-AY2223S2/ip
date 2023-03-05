package duke;

/**
 * Represents an event object with a "from date" and a "to date".
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Creates the event object.
     * @param taskName The description of the event.
     * @param from The start date of the event.
     * @param to The end date of the event.
     */
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
