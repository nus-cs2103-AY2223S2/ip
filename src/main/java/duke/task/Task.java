package duke.task;

import java.io.Serializable;

/**
 * Represents a task that has a description, an boolean whether it is done or not done
 */
public class Task implements Serializable {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "x" : " "); // mark done duke.task with X
    }

    /**
     * Mark a task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Mark a task as not done
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Parse the task to be stored in hard disk
     * @return string that will be stored in a file
     */
    public String parse() {
        return isDone + " | " + description;
    }
}