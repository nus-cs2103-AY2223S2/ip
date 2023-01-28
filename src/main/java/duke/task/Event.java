package duke.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String activity, String from, String to) {
        super(activity);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
