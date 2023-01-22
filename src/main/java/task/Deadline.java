package task;

import datetime.DateTime;

/**
 * A task with a deadline.
 */
public class Deadline extends Task {

    /**
     * The deadline of the task.
     */
    protected DateTime by;

    /**
     * Constructs a new deadline.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = new DateTime(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}