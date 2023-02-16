package duke.task;

/**
 * Task class.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task object.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns string of whether task is done or not.
     * @return String of status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns description of task.
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns status of task in 1 or 0.
     * @return Status of task in 1 or 0.
     */
    public String getStatusIconBinary() {
        return (isDone ? "1" : "0");
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns string of task.
     * @return String of task.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
