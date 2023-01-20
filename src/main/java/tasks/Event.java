package tasks;
/**
 * Events are tasks which start at a given date/time and end at a given date/time
 */
public class Event extends Task {
    private String description;
    private String start;
    private String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}