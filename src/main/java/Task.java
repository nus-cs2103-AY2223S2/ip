
/**
 * Contains information of a task
 * Contains description and completion status of the task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a task object
     * Completion status is set to false
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks task as done
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Changes status of task back to not done
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns completion status and description of task
     *
     * @return Completion status and description of task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] "
                + description;
    }
}
