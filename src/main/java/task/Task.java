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
     * Get icon representing the 'done' status of this Task.
     * @return the 'done' status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done smartduke.task with X
    }

    /**
     * Mark this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark this task as not done
     */
    public void markNotDone() {
        this.isDone = false;
    }

    public abstract String getSavedFormat();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}