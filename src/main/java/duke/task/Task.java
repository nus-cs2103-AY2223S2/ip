package duke.task;

import java.io.Serializable;

/**
 * Task is an abstract class representing a task to be performed.
 * It contains the description of a class and allows you to check whether a task has been completed.
 * It provides methods for marking or unmarking a task as complete.
 *
 * @author owen-yap
 *
 */
public abstract class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a task with the given description.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructs a task with the given description and completion status.
     *
     * @param description the description of the task
     * @param isDone the completion status of the task
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Get the status icon which indicates whether a task is complete.
     *
     * @return an X if the task is completed, and a space otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, marking it as incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Getter for checking if task is complete.
     *
     * @return true if the task is completed, false otherwise
     */
    public boolean isDone() { return this.isDone; }

    /**
     * The contains method checks if the target string is contained in the description field.
     *
     * @param target The string to search for in the description field.
     * @return true if the target string is found in the description, false otherwise.
     */
    public boolean contains(String target) {
        return this.description.contains(target);
    }

    /**
     * Formats the task as "[taskStatus] taskDescription".
     *
     * @return a string representation of the task
     */
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
