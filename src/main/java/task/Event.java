package task;

/**
 * Encapsulates a Task with a duration
 *
 */
public class Event extends Task {

    private String from;
    private String to;

    /**
     * Constructor
     *
     * @param description Description of the task.
     * @param from Start of the task.
     * @param to End of the task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Another Constructor
     *
     * @param description Description of the task.
     * @param from Start of the task.
     * @param to End of the task.
     * @param marked Status to show if the task is done.
     */
    public Event(String description, String from, String to, boolean marked) {
        super(description, marked);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" |from: %s to: %s", this.from, this.to);
    }
}
