package task;

/**
 * Class to support Event tasks.
 * Events are tasks that start at a specific date/time and end at a specific
 * date/time.
 */
public class Event extends Task {
    protected String from;
    protected String to;


    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString() +
                " (from: " + from + " to: " + to + ")";
    }
}
