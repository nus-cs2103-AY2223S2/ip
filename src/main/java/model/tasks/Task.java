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

    public String getTaskDesc() {
        return this.description;
    }

    public Boolean getIsComplete() {
        return this.isComplete;
    }

    public void setIsComplete(Boolean status) {
        this.isComplete = status;
    }

    public abstract String savedAs();

    @Override
    public String toString() {
        return String.format("[%s] %s", (this.isComplete ? "X" : " "), this.description);
    }
}
