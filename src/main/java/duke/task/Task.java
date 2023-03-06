package duke.task;

import java.io.Serializable;

/**
 * Creates the Task class.
 */
public abstract class Task implements Serializable, Comparable<Task> {
    /**
     * Consistent serialVersionUID value
     */
    private static final long serialVersionUID = -8933814718819670784L;
    /** Description of task */
    private final String description;
    /** Status of task */
    private boolean isDone;

    /**
     * Constructs a Task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets description of task.
     *
     * @return Description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets status of task, whether it is marked or not.
     *
     * @return Status of task.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Sets status of task, whether it is marked or not.
     *
     * @param isDone Status of task to be set to.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone; // Set done status
    }

    /**
     * Gets status icon of task.
     *
     * @return Status icon of task.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : "   "); // Mark done task with X
    }

    /**
     * Displays status of task and its description.
     *
     * @return Status of task and its description.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }

    @Override
    public int compareTo(Task o) {
        return description.compareTo(o.description);
    }
}
