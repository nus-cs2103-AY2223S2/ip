package seedu.duke;

/**
 * Encapsulates a task to be tracked by Duke.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    /**
     * Constructs a new task with description.
     *
     * @param description A sentence that describes the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the status, whether it is done or not.
     *
     * @return "X" if the task is done, otherwise " " is returned.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the text description of the task.
     *
     * @return A description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    private int isDoneToInt() {
        return isDone ? 1 : 0;
    }

    /**
     * Returns a String representation of the task in file.
     *
     * @return A String representation of the task in file.
     */
    public String taskInFileFormat() {
        return type + " | " + isDoneToInt() + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
