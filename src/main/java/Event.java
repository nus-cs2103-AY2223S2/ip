public class Event extends Task {

    protected String by;
    protected String to;

    public Event(String description, String by, String to) {
        super(description);
        this.by = by;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + by + " to: " + this.to + ")";
    }
}
