package duke.task;

/**
 * The duke.task.Task class represents a single task added by the user.
 *
 * @author Chia Jeremy
 */
public abstract class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Class constructor of a task.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Unmark task as done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the task status icon.
     *
     * @return the 'X' icon if a task is done. Otherwise, it is empty
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the task description.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
