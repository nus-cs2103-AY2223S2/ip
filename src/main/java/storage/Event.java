package storage;

public class Event extends Task {

    protected String from;
    protected String to;

    /**
     * Creates a new Event with the specified description
     *
     * @param description The specified description
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
