/**
 * The Task class encapsulates the description of the task and its 'done' status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Get icon representing the 'done' status of this Task.
     * @return the 'done' status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark this task as not done
     */
    public void markNotDone() {
        this.isDone = false;
    }
}