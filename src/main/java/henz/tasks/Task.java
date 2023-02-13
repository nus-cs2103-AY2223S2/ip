package henz.tasks;

/**
 * Task is the abstract base class for all tasks
 * @author Leng Wei Cong, Justin
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor.
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task based {@link #isDone}.
     * @return a string that repesents true as X and false as " "
     */
    private String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the boolean of the task done status.
     * @return the boolean of the task done status
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a string representation of the task.
     * @return a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Updates task description and returns the task instance.
     * @param description
     * @return Task the instance
     */
    public Task updateDescription(String description) {
        this.description = description;

        return this;
    }
}
