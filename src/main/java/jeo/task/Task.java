package jeo.task;

/**
 * Represents a generic task.
 * @author Goh Jun How
 * @version 0.1
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Acts as the constructor taking in the task description, to be inherited.
     * @param description String describing the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the task description.
     * @return String representing the task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the status icon.
     * @return String representing either "X" or " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done jeo.task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkFromDone() {
        isDone = false;
    }

    /**
     * Gets the string representation of a task.
     * @return String representing a task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
