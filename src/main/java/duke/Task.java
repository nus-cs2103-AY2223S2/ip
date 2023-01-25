package duke;

/**
 * Represents a task with modifiable status.
 */
public abstract class Task {

    protected String content;
    protected boolean isDone;
    protected Task(boolean status, String content) {
        this.isDone = status;
        this.content = content;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    protected void markAsDone() {
        isDone = true;
    }

    protected void markAsUndone() {
        isDone = false;
    }

    protected String getTypeIcon() {
        return " ";
    }

    /**
     * @return String representation of the generic task
     */
    @Override
    public String toString() {
        return content;
    }

    // Status and message
    protected String fullMessage() {
        return String.format("[%s][%s] %s", getTypeIcon(), getStatusIcon(), this);
    }

    protected abstract String fileMessage();

}
