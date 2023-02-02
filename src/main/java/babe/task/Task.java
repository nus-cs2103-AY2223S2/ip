package babe.task;

/**
 * <code>Task</code> is an abstract class that specifies functions required by its concrete
 * subclasses (namely, <code>Event</code>, <code>ToDo</code> and <code>Deadline</code> to have.
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
     * Done status for this <code>Task</code>.
     */
    boolean isDone = false;

    /**
     * Contents of this item.
     */
    String description = "";

    /**
     * Constructor for <code>Task</code>s
     *
     * @param content A String that is the description of this item.
     */
    public Task(String content) {
        this.description = content;
    }

    @Override
    /**
     * Returns String representation of the <code>Task</code> item.
     *
     * @return A String that represents the <code>Task</code> item, including its description and done status.
     */
    public abstract String toString();

    /**
     * Mark the <code>Task</code> as Done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Mark the <code>Task</code> as Undone.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns String representation of the <code>Task</code> item for data storage.
     * The String representation includes its done status, subtype and description. The delimiter "|"
     * is used.
     *
     * @return A String representing this <code>Task</code> object, specifically for
     *         <code> Storage </code>'s input.
     */
    public abstract String toSaveFormat();

}
