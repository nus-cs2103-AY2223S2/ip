package babe.task;

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
     * Returns String representation of the Task item.
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

    /**
     * Returns String representation of the Task item for data storage.
     * Saves the content of the Task, including its isDone, subtype and description into a String with the delimiter
     * "|" to be written into a data file.
     *
     * @return A String representing this Task object.
     */
    public abstract String toSaveFormat();

}
