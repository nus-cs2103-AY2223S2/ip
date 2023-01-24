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
    private boolean marked;

    /**
     * Creates a task.
     *
     * @param desc Description of the task.
     * @param marked Task marking.
     */
    public Task(String desc, boolean marked) {
        this.description = desc;
        this.marked = marked;
    }

    /**
     * Returns the symbol to show if the task is done or not.
     *
     * @return Task marked symbol.
     */
    private String isMarkedSymbol() {
        return this.marked ? "X" : " ";
    }

    /**
     * Returns the task checkbox and description.
     *
     * @return Task details.
     */
    public String toString() {
        return "[" + this.isMarkedSymbol() + "] " + this.description;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        this.marked = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmark() {
        this.marked = false;
    }

    /**
     * Returns the string to store the task in a file.
     *
     * @return File storage version of the task.
     */
    public String toTaskStorageString() {
        String marked = String.valueOf(this.marked);
        return marked + "|" + this.description;
    }
}
