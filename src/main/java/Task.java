/**
 * A class that represents a Task
 */
public class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Initialize an Task object with the given values.
     *
     * @param name The name of the task
     * @return A deadline instance
     */
    public Task(String name) {
        this.taskName = name;
        isDone = false;
    }

    /**
     * Mark the task as done
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Mark the task as undone
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Get the status of the task, whether it is done or not
     *
     * @return A string representation of the task's status
     */
    public String getStatus() {
        return isDone ? "X" : " ";
    }

    /**
     * Get the name of the task
     *
     * @return A string representation of the task's name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns the string representation of the task, including
     * whether the task is done or not.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "] " + this.taskName;
    }
}
