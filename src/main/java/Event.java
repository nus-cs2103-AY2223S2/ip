public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
    }

    public String interpretTaskToString() {
        return "E | " + this.getStatusIcon() + " | " + this.description + " | " + this.from + " " + this.to;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from:" + from + " to:" + to +")";
    }
}