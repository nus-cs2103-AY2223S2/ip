package duke.tasks;

/**
 * The abstract class of tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    abstract public String getTaskType();
    abstract public String getTimeline();

    /**
     * Indicates whether the task is completed.
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task. 
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Provides the description of the task.
     * @return
     */
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
