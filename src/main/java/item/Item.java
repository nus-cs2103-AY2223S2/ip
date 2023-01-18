package item;

/**
 * Interface for list items in Babe,
 */
public abstract class Item {
    /**
     * String icon for marked.
     */
    static String MARKED = "[X]";

    /**
     * String icon for unmarked.
     */
    static String UNMARKED = "[ ]";

    /**
     * Done status for this list item.
     */
    boolean isDone = false;

    /**
     * Contents of this item.
     */
    String content = "";

    /**
     * Constructor for Items
     *
     * @param content A String that is the content of this item.
     */
    public Item( String content ) {
        this.content = content;
    }

    @Override
    /**
     * Prints the item with its corresponding format.
     * Each subclass of Item will have a designated String format.
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
