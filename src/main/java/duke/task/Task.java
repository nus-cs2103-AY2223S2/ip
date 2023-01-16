package duke.task;

/**
 * Represents a task.
 */
public abstract class Task {
    private final boolean isDone;
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

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public Task setDone(boolean isDone) {
        return createTask(isDone, description);
    }

    public Task setDescription(String description) {
        return createTask(isDone, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

    /**
     * Returns a Task object created using the provided arguments.
     *
     * @param isDone Is the task done.
     * @param description Description of the task.
     * @return Task object created using the provided arguments.
     */
    protected abstract Task createTask(boolean isDone, String description);
}
