package duke.task;

/**
 * Represents a task.
 */
public abstract class Task {
    private boolean isDone;
    private final String description;

    /**
     * Creates a Task object.
     *
     * @param isDone Is the task done.
     * @param description Description of the task.
     */
    public Task(boolean isDone, String description) {
        this.isDone = isDone;
        this.description = description;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}
