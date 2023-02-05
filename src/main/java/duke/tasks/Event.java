package duke.tasks;

/**
 * Event Task class.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for Event object.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + super.split(this.from) + " to: " + super.split(this.to) + ")";
    }

    @Override
    public String toText() {
        int done;
        if (this.isDone) {
            done = 1;
        } else {
            done = 0;
        }
        return "E" + " | " + done + " | " + this.description + " | " + this.from + " | " + this.to;
    }
}
