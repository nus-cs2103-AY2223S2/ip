package duke;

/**
 * A class that represents a Task
 */
public class Task {
    /** Name of the task */
    private String taskName;

    /** Status of the class */
    private boolean isDone;

    /**
     * Initializes an Task object with the given values.
     *
     * @param name The name of the task
     * @return A task instance
     */
    public Task(String name) {
        this.taskName = name;
        isDone = false;
    }

    /**
     * Initializes an Task object with the given values.
     *
     * @param name The name of the task
     * @param isDone The status of the task
     * @return A task instance
     */
    public Task(String name, boolean isDone) {
        this.taskName = name;
        this.isDone = isDone;
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
        return isDone ? "X" : "O";
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
        return getStatus() + " | " + this.taskName;
    }
}
