package wtd.task;

/**
 * Represents a task.
 */
public abstract class Task {
    /** The description of the task. */
    protected String description;

    /** Whether the task is done. */
    protected boolean isDone;

    /**
     * Constructor for Task.
     * 
     * @param description the description of the task.
     * @param isDone whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status icon of the task.
     * 
     * @return the status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     * 
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the task as done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as not done.
     */
    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     * 
     * @return the string representation of the task.
     */
    abstract public String toFile();
}
