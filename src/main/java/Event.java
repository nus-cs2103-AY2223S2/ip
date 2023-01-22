public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + " " + super.getStatusIcon() + " " + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String toSaveString() {
        // E | 0 | project meeting | Aug 6th 2-4pm
        return "E | " + (isDone ? 1 : 0) + " | " + super.toString() + " | " + from + " | " + to;
    }
}
