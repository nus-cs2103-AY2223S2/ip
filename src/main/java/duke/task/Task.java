package duke.task;

/**
 * Represents a task.
 */
public class Task {
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
        return new Task(isDone, description);
    }

    public Task setDescription(String description) {
        return new Task(isDone, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}
