package duke.task;

/**
 * Represents a task.
 */
public class Task {
    protected String desc;
    protected boolean isDone;

    /**
     * Returns a task.
     * @param description description of task.
     */
    public Task(String description) {
        this.desc = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return description
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Sets the status of the task.
     * @param isDone final status of the task.
     */
    public void setStatus(Boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns status icon depending on whether task is marked.
     *
     * @return X for marked, empty if unmarked,
     */
    public String getStatusIcon() {
        return (isDone) ? "X" : " "; // mark done task with X
    }

    /**
     * Returns format of task for printing to user.
     * @return string format.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.desc);
    }

    /**
     * Return format of task for backup.
     * @return string format.
     */
    public String toBackup() {
        return String.format("%s | %s", (isDone) ? "1" : "0", this.desc);
    }
}
