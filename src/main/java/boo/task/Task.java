package boo.task;

/**
 * Represents a task that can be kept track of.
 */
public abstract class Task {
    /** The name of the task. */
    protected String taskName;
    /** The status of the task. **/
    protected boolean isDone = false;

    /**
     * Constructs a user task.
     *
     * @param taskName The name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Sets status of task to be done
     */
    public void setDoneStatus() {
        isDone = true;
        assert isDone;
    }

    /**
     * Sets status of task to be undone
     */
    public void setUndoneStatus() {
        isDone = false;
        assert !isDone;
    }

    /**
     * Gets the status of the task with the task name.
     *
     * @return a {@code String} indicating the type and status of the task.
     */
    public abstract String getStatusOfTaskInString();

    /**
     * Gets the status of the task.
     *
     * @return true if task is done, else return false.
     */
    public boolean getStatusOfTask() {
        return isDone;
    }

    /**
     * Gets the name of the task.
     *
     * @return the name of the task.
     */
    public String getNameOfTask() {
        return this.taskName;
    }
}
