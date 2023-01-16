public class Event extends Task {
    protected String by;
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "\t[E]" + super.toString() + " (from: " + from + "to: " + to + ")";
    }
}
