package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
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
     * Sets task as done or not done.
     *
     * @param isDone Status of the task to be set.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
