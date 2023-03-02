package duke.tasks;

/**
 * The Event class extends the Task class and adds a start and end time to the description.
 */
public class Event extends Task {
    protected String start;
    protected String end;

    /**
     * A constructor that takes in a description, start and end time and sets the description, start
     * and end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + this.start + "to:" + this.end + ")";
    }
}
