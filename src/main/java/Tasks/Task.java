package tasks;

/**
 * This abstract class is used to represent a Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return The status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
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
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Sets the description to the user input.
     * @param userInput The new description.
     */
    public void setDescription(String userInput) {
        this.description = userInput;
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the task.
     * @return The description of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
