public class Event extends Task {

    protected String by;
    protected String from;

    public Event(String description, String by, String from) {
        super(description);
        this.by = by;
        this.from = from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.by + ")";
    }

}
