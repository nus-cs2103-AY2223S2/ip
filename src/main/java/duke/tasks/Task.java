package duke.tasks;

/**
 * An abstract class to represent a Task object.
 */
public abstract class Task {
    /** The name or description of this task. **/
    protected String name;

    /** Represents if the task is marked as done or not. **/
    private boolean isDone;

    /**
     * Constructor for a task.
     * @param name The name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Format when saving task to local storage.
     * @return A formatted string
     */
    public abstract String toSaveFormat();

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
