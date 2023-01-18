package task;

/**
 * ToDo class for Babe's list.
 * Extends abstract class Item.
 */
public class ToDo extends Task {

    /**
     * Constructor for ToDos
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

}
