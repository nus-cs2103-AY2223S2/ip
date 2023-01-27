package task;
/**
 * Task is the superclass of the activities that can be stored within Duke.
 */
public abstract class Task {
    private boolean status;
    private final String taskName;


    /**
     * Constructs a Task with the provided name and status.
     *
     * @param name The name of this task
     * @param status The status of this task
     */
    protected Task(String name, Boolean status) {
        this.taskName = name;
        this.status = status;
    }

    /**
     * Returns the name of the Task.
     *
     * @return The name of this Task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the state of the task alongside with the task name.
     * Example output:
     * `[X] read book` would mean that the task `read book` is complete.
     * `[ ] read book` would mean that the task `read book` is incomplete.
     *
     * @return The String representation of this task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatus() ? "X" : " ", this.taskName);
    }

    /**
     * Returns the String representation of the task delimited by commas.
     *
     * @return The string representation of this task in CSV format
     */
    public abstract String toCsv();

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
