package babe.task;

/**
 * <code>ToDo</code> class represents a simple <code>Task</code> with just a description and done status.
 * It extends from <code>Task</code> as implied.
 */
public class ToDo extends Task {

    /**
     * Constructor for <code>ToDo</code>s
     *
     * @param content A String representing the content of this item.
     */
    public ToDo(String content) {
        super(content, "T");
    }

    @Override
    /**
     * Returns String representation of the <code>ToDo</code> item.
     *
     * @return A String that represents the <code>ToDo</code> item, including its description and done status.
     */
    public String toString() {
        return String.format("[%s] ", this.letterType)
                + (this.isDone ? this.MARKED : this.UNMARKED) + " "
                + this.description;
    }

    /**
     * Returns String representation of the <code>ToDo</code> object for data storage.
     * The format is as such: T|{done status}|{description}
     *
     * @return A String representing this <code>ToDo</code> object.
     */
    public String toSaveFormat() {
        return "T|" + (this.isDone ? 1 : 0) + "|" + this.description;
    }

    ;
}
