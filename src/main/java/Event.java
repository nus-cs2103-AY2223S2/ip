public class Event extends Task {
    protected String from;
    protected String to;

    Event(String title, String from, String to) {
        super(title);
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}