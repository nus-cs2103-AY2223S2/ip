package duke.task;

/**
 * Task is the abstract base class for the other 3 types of Task: Todo, Deadline and Event.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for Task.
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Gets the description for a task.
     * @return The String description for a task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Mark a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark a task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets a String representation of the task status.
     * @return 'X' if task is done and " " if not done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }
    public abstract String toData();

    @Override
    public String toString() {
        return "Added: " + this.description;
    }
}
