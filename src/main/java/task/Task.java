package task;

/**
 * Parent class Task
 * Inherited by ToDo, Deadline and Event Class
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor.
     *
     * @param description Description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Another Constructor
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns description of task.
     *
     * @return description of task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns isDoneStatus in String form.
     * X indicating marked, and whitespace indicating unmarked.
     *
     * @return isDone
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}


