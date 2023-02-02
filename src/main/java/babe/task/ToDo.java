package babe.task;

/**
 * ToDo class for Babe's list.
 * Extends abstract class Item.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDos
     *
     * @param content A String representing the content of this item.
     */
    public ToDo(String content) {
        super(content);
    }

    @Override
    /**
     * Prints a ToDo item in its specified format.
     */
    public String toString() {
        return (this.isDone ? this.MARKED : this.UNMARKED) + " " + this.description;
    }

    /**
     * Returns String representation of the ToDo object for data storage.
     * Saves the content of the ToDo, including its isDone and description into a String with the delimiter
     * "|" to be written into a data file.
     *
     * @return A String representing this ToDo object.
     */
    public String toSaveFormat() {
        return "T|" + (this.isDone ? 1 : 0) + "|" + this.description;
    }

    ;
}
