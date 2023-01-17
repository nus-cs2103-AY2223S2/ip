public class Event extends Task {
    protected String from;
    protected String to;
    protected boolean isSet;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getStatus() {
        return (isSet ? "D" : " "); // mark done task with E
    }

    @Override
    public String toString() {
        return "[" + this.getStatus() + "]" + super.toString() + " (from: " + this.from + "to: " + this.to + ")";
    }
}
