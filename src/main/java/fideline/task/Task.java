package fideline.task;

/**
 * Representation of a task that can be completed.
 *
 * @author Fun Leon
 */
public abstract class Task {

    /** Title describing the task */
    private String description;

    /** Status that indicates if the task is done */
    private boolean isDone;

    /**
     * Constructs a task object that has a description. Tasks
     * are not done by default.
     *
     * @param description Title given to the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns string representation of the task.
     *
     * @return String representing the task.
     */
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }

    /**
     * Changes status of the task to done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Changes status of the task to not done.
     */
    public void unmark() {
        this.isDone = false;
    }

}
