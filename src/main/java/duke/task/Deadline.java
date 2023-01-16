package duke.task;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    /**
     * Creates a DeadlineTask object.
     *
     * @param isDone Is the task done.
     * @param description Description of the task.
     * @param deadline Deadline of the task.
     */
    public Deadline(boolean isDone, String description, String deadline) {
        super(isDone, String.format("%s (by: %s)", description, deadline));
    }

    private Deadline(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return String.format("[D]%s", super.toString());
    }

    @Override
    protected Task createTask(boolean isDone, String description) {
        return new Deadline(isDone, description);
    }
}
