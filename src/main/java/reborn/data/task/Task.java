package reborn.data.task;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task.
     *
     * @param description Description of what the task is.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * String representation of task for storage.
     *
     * @return String of task to store.
     */
    public abstract String storageStr();

    /**
     * Checks status of task.
     *
     * @return String of status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Checks status of task.
     *
     * @return String of status.
     */
    public String getStatusValue() {
        return (isDone ? "1" : "0"); // mark done task with 1
    }

    /**
     * Marks and displays marked.
     * @return String of marked.
     */
    public String outputMarked() {
        isDone = true;
        return "You finally did it huh:\n";
    }

    /**
     * Unmarks and displays unmarked.
     * @return String of unmarked.
     */
    public String outputUnmarked() {
        isDone = false;
        return "How did you end up undoing this:\n";
    }

    /**
     * Marks task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Checks if task description contains given string.
     *
     * @param string String to check with.
     * @return Boolean whether task description contains string.
     */
    public boolean contains(String string) {
        return this.description.contains(string);
    }

    /**
     * String representation of task.
     *
     * @return String of task.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
