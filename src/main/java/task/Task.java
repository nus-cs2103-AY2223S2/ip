package task;
/**
 * Task is the superclass of the activities that can be stored within core.Duke.
 */
public abstract class Task {
    private boolean isComplete;
    private final String taskDescription;

    /**
     * This method constructs a Task with the provided name and status.
     *
     * @param description The description or name of the task
     * @param isComplete The status of this task
     */
    protected Task(String description, Boolean isComplete) {
        this.taskDescription = description;
        this.isComplete = isComplete;
    }

    /**
     * This method returns the name of the Task.
     *
     * @return The description or name of this Task.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * This method returns the state of the task alongside with the task name.
     *
     * Example output:
     * `[X] read book` would mean that the task `read book` is complete.
     * `[ ] read book` would mean that the task `read book` is incomplete.
     *
     * @return The String representation of this task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getComplete() ? "X" : " ", this.taskDescription);
    }

    /**
     * Returns the String representation of the task delimited by commas.
     * @return The string representation of this task in CSV format
     */
    public abstract String toCsv();

    public boolean getComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        this.isComplete = complete;
    }
}
