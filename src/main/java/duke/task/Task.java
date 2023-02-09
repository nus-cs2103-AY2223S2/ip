package duke.task;

import java.io.Serializable;

/**
 * Class for task object.
 */
public class Task implements Serializable {
    private String taskName;
    private boolean isDone;

    /**
     * Constructor for Task object.
     *
     * @param taskName The name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Mark the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmark the task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns true if taskName contains keyword and false otherwise
     *
     * @return True if taskName contains keyword and false otherwise
     */
    public boolean contains(String keyword) {
        return this.taskName.contains(keyword);
    }

    /**
     * Returns string representation of the task and its status.
     *
     * @return String representation of the task and the marked status.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.taskName;
        } else {
            return "[ ] " + this.taskName;
        }
    }
}
