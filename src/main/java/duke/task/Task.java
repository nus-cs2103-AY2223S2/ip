package duke.task;

/**
 * The Task class.
 */
public class Task {
    protected String description;
    protected boolean isDone = false;
    protected Priority priority;


    /**
     * Constructor to create a Task.
     */
    public Task(String description) {
        assert !description.isEmpty();
        this.description = description;
        this.priority = Priority.LOW;
        this.isDone = false;
    }

    /**
     * Return the task's priority.
     *
     * @return String representation of the priority.
     */
    public String getPriority() {
        return priority.toString();
    }

    /**
     * Return the task's priority in short form.
     *
     * @return String representation of the priority in short form.
     */
    public String getShortPriority() {
        return priority.shortString();
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * Returns X if the task is done, and whitespace if the task is not done.
     *
     * @return The string representation of whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
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
     * Checks if the task is done.
     *
     * @return Boolean representing if the task is done or not.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * String representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + "[" + priority + "] " + getDescription();
    }
}
