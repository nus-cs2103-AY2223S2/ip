package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return X or a single space depending on whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the status of the task as an integer.
     *
     * @return 1 or 0 depending on whether the task is done.
     */
    public int getStatusIconInt() {
        return (isDone ? 1 : 0);
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Mark task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
