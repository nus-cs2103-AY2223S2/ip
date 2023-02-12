package task;

/**
 * Encapsulates a Task with Deadline.
 */
public class Deadline extends Task {

    private String by;

    /**
     * Constructor.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Another Constructor.
     *
     * @param description Description of the task.
     * @param by Deadline of the Task.
     * @param marked Status to show if the task is done.
     */
    public Deadline(String description, String by, boolean marked) {
        super(description, marked);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " |by: " + by;
    }
}
