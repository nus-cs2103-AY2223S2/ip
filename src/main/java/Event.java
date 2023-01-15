public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean bool, String from, String to) {
        super(description, bool);
        this.from = from;
        this.to = to;
    }

    @Override
    public Event markAsDone() {
        return new Event(description, true, from, to);
    }

    @Override
    public Event unmarkAsDone() {
        return new Event(description, from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}