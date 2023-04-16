package model.tasks;

/**
 * Class of task
 */
public abstract class Task {
    private final String description;
    private Boolean isComplete = false;

    /**
     * Constructor for the Task Class
     * @param description name of the class
     * @param c type of the task
     * @param isDone whether the task is done
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the task
     * @return String
     */
    public String getTaskDesc() {
        return this.description;
    }

    /**
     * Returns the status of the task
     * @return Boolean
     */
    public Boolean getIsComplete() {
        return this.isComplete;
    }

    /**
     * Sets the status of the task
     * @param status The status of the task
     */
    public void setIsComplete(Boolean status) {
        this.isComplete = status;
    }

    /**
     * Returns the string representation of the task to be saved
     * @return String
     */
    public abstract String savedAs();

    /**
     * Returns a string representation of this task
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", (this.isComplete ? "X" : "  "), this.description);
    }
}
