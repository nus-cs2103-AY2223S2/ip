package tasks;

/**
 * Represents a scheduled event
 */
public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Creates a new Event object with the given description, starting time and end time.
     *
     * @param description the description of the event.
     * @param from the starting time of the event.
     * @param to the end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a new Event object with the given description, completion status, starting time and end time.
     *
     * @param description the description of the event.
     * @param isDone whether the event is completed.
     * @param from the starting time of the event.
     * @param to the end time of the event.
     */
    public Event(String description, boolean isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String parseToSave() {
        return "E" + " | " + super.parseToSave() + " | " + from + " | " + to;
    }
}
