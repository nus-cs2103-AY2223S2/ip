package duke;

/**
 * Task is an object that is to be done or undertaken by the user
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * returns a task object with a description that is marked undone
     * @param description Describes the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns whether the task has been completed, "X" indicates done,
     * " " indicates undone
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as undone
     */
    public void markAsUndone() {
        isDone = false;
    }
}