import java.io.Serializable;

/**
 * A class to represent a task.
 * @author mmaimer33
 */
public abstract class Task implements java.io.Serializable {
    protected String description;
    private boolean isDone;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the representative status mark for the task.
     * @return 'X' if done; ' ' otherwise.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Mark task as completed.
     */
    public void complete() {
        this.isDone = true;
    }

    /**
     * Mark task as incomplete.
     */
    public void uncomplete() {
        this.isDone = false;
    }

    public abstract String getTaskType();
    public abstract String getDescription();
}
