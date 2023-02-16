package duke.task;


/**
 * Class of Event which creates the task with to and from timing.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for Event.
     *
     * @param activity the activity of the task written by the user.
     * @param from the start datetime of the task.
     * @param to the end datetime of the task.
     */
    public Event(String activity, String from, String to) {
        super(activity);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String res = "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
        return res;
    }
}
