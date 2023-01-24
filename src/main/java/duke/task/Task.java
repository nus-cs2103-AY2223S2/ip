package duke.task;

/**
 * Represents a task.
 *
 * @author wz2k
 */
public abstract class Task {
    /**
     * The task description.
     */
    private String description;

    /**
     * Marking to show if task is done.
     */
    private boolean isMarked;

    /**
     * Constructor for duke.task.Task class.
     *
     * @param description description of the task.
     */
    public Task(String description, boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    /**
     * This method returns the symbol to show if the task is done or not.
     *
     * @return task marked symbol.
     */
    private String isMarkedSymbol() {
        return isMarked ? "X" : " ";
    }

    /**
     * This method returns the task checkbox and description.
     *
     * @return task details.
     */
    public String toString() {
        return "[" + isMarkedSymbol() + "] " + description;
    }

    /**
     * This method marks the task as done.
     */
    public void mark() {
        isMarked = true;
    }

    /**
     * This method marks the task as not done.
     */
    public void unmark() {
        isMarked = false;
    }

    public String toTaskStorageString() {
        String marked = String.valueOf(isMarked);
        return marked + "|" + description;
    }
}
