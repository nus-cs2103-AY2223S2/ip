package task;

/**
 * Interface for list items in Babe,
 */
public abstract class Task {
    /**
     * String icon for marked.
     */
    static String MARKED = "[X]";

    /**
     * String icon for unmarked.
     */
    static String UNMARKED = "[ ]";

    /**
     * Done status for this Task.
     */
    boolean isDone = false;

    /**
     * Contents of this item.
     */
    String description = "";

    /**
     * Constructor for Tasks
     *
     * @param content A String that is the description of this item.
     */
    public Task(String content) {
        this.description = content;
    }

    @Override
    /**
     * Prints the item with its corresponding format.
     * Each subclass of Task has a designated String format.
     */
    public abstract String toString();

    /**
     * Mark the item as Done.
     * Assign truth value to isDone.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Mark the item as Undone.
     * Assign false value to isDone.
     */
    public void unmark() {
        isDone = false;
    }

}
