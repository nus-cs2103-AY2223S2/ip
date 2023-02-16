package alfred.task;

/**
 * Contains various methods of a task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates an instance of task
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public abstract String getTaskType();

    public abstract String getStorageDetails();

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
