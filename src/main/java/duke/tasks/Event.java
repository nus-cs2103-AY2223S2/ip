package duke.tasks;

public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for a Event object.
     *
     * @param description The Event description.
     * @param from The start date of the Event.
     * @param to The end date of the Event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
