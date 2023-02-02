public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public Event(String description, String from, String to) {
        this(description, from, to, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }

    @Override
    public String saveAsStr() {
        return "E" + super.saveAsStr() + "~%~" + from + "~%~" + to;
    }
}
