public class Event extends Task {

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + getStatusIcon() + " " + description + " (from: " + from + " to: " + to + ")";
    }
}
