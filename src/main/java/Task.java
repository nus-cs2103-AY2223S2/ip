/**
 * Represents a task entry with a description
 * and a boolean representing its status.
 */
public class Task {
    // Stores the description of the task.
    protected String description;
    // Stores the status of the task.
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Return the status of task represented with a String.
     * "X" if the task is done, "" if it is not done.
     *
     * @return String representation of task status.
     */
    public String getStatusIcon() {
        // Marks the finished task with "X".
        return (isDone ? "X" : " ");
    }

    /**
     * Overrides the toString method of the Object class.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {

        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        isDone = false;
    }
}
