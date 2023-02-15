package task;

/**
 * Class that store details of a task.
 */
public abstract class Task {
    private final String description;
    private boolean done;

    /**
     * Constructs Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        done = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task to be done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Sets the take to be not done.
     */
    public void markNotDone() {
        this.done = false;
    }

    /**
     * Returns whether the task is done.
     *
     * @return a boolean representing whether task is done.
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Returns the description of the task to be written in file.
     *
     * @return Details of task.
     */

    public abstract String toText();

    /**
     * Returns the description of the task to be output to users.
     *
     * @return Details of task.
     */
    @Override
    public String toString() {
        return "[" + (this.isDone() ? "X" : " ") + "] " + this.description;
    }
}
