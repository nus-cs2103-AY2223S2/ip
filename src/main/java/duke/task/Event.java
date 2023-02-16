package duke.task;

/**
 * Class for event object.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Constructor for Event object.
     *
     * @param taskName The name of the task.
     * @param from The start date of event.
     * @param to The end date of event.
     */
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns string representation of the task and its status.
     *
     * @return String representation of the task and the marked status.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from + " to: " + this.to + ")";
    }
}
