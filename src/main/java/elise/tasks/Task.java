package elise.tasks;

/**
 * Represents a task with modifiable status.
 */
public abstract class Task {

    protected String content;
    protected boolean isDone;

    /**
     * Constructor of task.
     *
     * @param status Completed or not.
     * @param content Message body.
     */
    public Task(boolean status, String content) {
        this.isDone = status;
        this.content = content;
    }

    /**
     * Returns status icon of the task.
     *
     * @return Status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks task as not completed.
     */
    public void markAsUndone() {
        isDone = false;
    }

    protected abstract String getTypeIcon();

    /**
     * @return String representation of the generic task
     */
    @Override
    public String toString() {
        return content;
    }

    /**
     * Returns String representation of task with additional details.
     *
     * @return Full details of the task.
     */
    public String fullMessage() {
        return String.format("[%s][%s] %s", getTypeIcon(), getStatusIcon(), this);
    }

    public abstract String fileMessage();
}
