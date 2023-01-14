package duke.task;

/**
 * Event
 */
public class Event extends Task {

    private String from;
    private String to;

    public Event(String title, String from, String to) {
        this(title, false, from, to);
    }

    public Event(String title, boolean isDone, String from, String to) {
        super(title, isDone);
        this.from = from;
        this.to = to;
    }

    public String toCsv() {
        return "E," + super.toCsv() + "," + from + "," + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
