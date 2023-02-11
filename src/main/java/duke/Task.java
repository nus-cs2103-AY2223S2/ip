package duke;

/**
 * Represent a Task.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Creates new Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves the status of the task.
     *
     * @return X if the task is done or a whitespace if it is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark the task as done.
     */
    public void unMarkAsDone() {
        this.isDone = false;
    }

    /**
     * Overrides the default Object::toString.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]";
    }
}