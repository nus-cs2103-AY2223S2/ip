package duke;


/**
 * Represents a task with a description and a completion status.
 *
 * @author @tricixg
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType = "T";
    /**
     * Constructs a Task with a description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Constructs a Task with a description and a completion status.
     *
     * @param isDone The completion status of the task.
     * @param description The description of the task.
     */
    public Task(boolean isDone, String description) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the format of the task when saved.
     *
     * @return The saved format of the task.
     */
    public abstract String saveFormat();

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task as a String.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the completion status of the task.
     *
     * @return The completion status of the task as a boolean.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "]" + this.description;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }
}
