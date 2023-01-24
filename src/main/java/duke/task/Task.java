package duke.task;

/**
 * Represents a task of the user.
 *
 * @author wz2k
 */
public abstract class Task {
    /** Task description */
    private String description;

    /** Marking to show if task is done */
    private boolean isMarked;

    /**
     * Creates a task.
     *
     * @param description Description of the task.
     * @param isMarked Task marking.
     */
    public Task(String description, boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    /**
     * Returns the symbol to show if the task is done or not.
     *
     * @return Task marked symbol.
     */
    private String isMarkedSymbol() {
        return isMarked ? "X" : " ";
    }

    /**
     * Returns the task checkbox and description.
     *
     * @return Task details.
     */
    public String toString() {
        return "[" + isMarkedSymbol() + "] " + description;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isMarked = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        isMarked = false;
    }

    /**
     * Returns the string to store the task in a file.
     *
     * @return File storage version of the task.
     */
    public String toTaskStorageString() {
        String marked = String.valueOf(isMarked);
        return marked + "|" + description;
    }
}
