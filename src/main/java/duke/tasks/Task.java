package duke.tasks;

/**
 * Represents user task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor of Task class.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon of the task.
     *
     * @return status icon of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return a description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks task as done.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks task as incomplete.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     *  Returns string representation of the object.
     *
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
