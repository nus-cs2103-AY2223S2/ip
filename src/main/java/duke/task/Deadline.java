package duke.task;

/**
 * Subclass of task representing a deadline task
 */
public class Deadline extends Task {
    private static final String TYPE = "D";
    private String dueDateTime;

    /**
     * Constructor for Deadline Class
     *
     * @param description desc of the task
     * @param by task's deadline
     * @param isDone whether the task is done
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone, TYPE);
        this.dueDateTime = by;
    }

    /**
     * Constructor for creating a new Deadline task
     *
     * @param description desc of the task
     * @param by task's deadline
     */
    public Deadline(String description, String by) {
        super(description, false, TYPE);
        this.dueDateTime = by;
    }

    /**
     * Returns the string representation of a deadline task
     *
     * @return string representation of a deadline task
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), dueDateTime);
    }
}
