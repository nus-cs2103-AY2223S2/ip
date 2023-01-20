package task;

/**
 * A task with a deadline.
 */
public class Deadline extends Task {

    /**
     * The deadline of the task.
     */
    protected String by;

    /**
     * Constructs a new deadline.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}