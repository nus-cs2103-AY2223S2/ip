package duke.task;

/**
 * The type Task.
 */
public abstract class Task {

    /**
     * The Description.
     */
    protected String description;

    /**
     * The Is done.
     */
    protected boolean isDone;

    /**
     * Instantiates a new Task.
     *
     * @param description the description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets status icon.
     *
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Marks task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Mark task as incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Translate Task object into a savable string for storage in a text file.
     *
     * @return the string
     */
    public abstract String toSaveableString();

}
