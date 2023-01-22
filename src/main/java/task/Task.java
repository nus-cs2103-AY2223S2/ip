package task;

import duke.DukeException;

/**
 * Task encapsulates the description of a task and its 'done' status.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param description The task description.
     * @throws DukeException If there is no description indicated.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets icon representing the 'done' status of this Task.
     * @return the 'done' status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done smartduke.task with X
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Gets the formatted string representing this task to be saved to the local file.
     * @return The formatted string.
     */
    public abstract String getSavedFormat();

    /**
     * Gets the string representation of this task to be displayed on the Ui.
     * @return The string representation of this task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}