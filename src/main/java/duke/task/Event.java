package duke.task;


/**
 * Class of Event which creates the task with to and from timing.
 */
public class Event extends Task {
    private String from;
    private String to;

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
