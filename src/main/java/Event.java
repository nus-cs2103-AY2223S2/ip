/**
 * The Event of tasks.
 * Inherits from the superclass Task.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String nameOfTask, String from, String to) {
        super(nameOfTask);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + " )";
    }
}
