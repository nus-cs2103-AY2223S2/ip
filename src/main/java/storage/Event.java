package storage;

public class Event extends Task {

    protected String from;

    /**
     * Creates a new Event with the specified description
     *
     * @param description The specified description
     */
    public Event(String description, String from) {
        super(description);
        this.from = from;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s)", super.toString(), from);
    }
}
