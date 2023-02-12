package babe.task;

/**
 * <code>Deadline</code> class represents a <code>Task</code> that has a deadline.
 * It extends from <code>Task</code> as implied.
 */
public class Deadline extends Task {

    /**
     * A date/time for this <code>Deadline</code>
     */
    private String deadline = "";

    /**
     * Constructor for <code>Deadline</code> item.
     *
     * @param content  Description of this <code>Deadline</code>.
     * @param deadline Date for the deadline of this item.
     */
    public Deadline(String content, String deadline) {
        super(content, "D");
        this.deadline = deadline.stripTrailing();
    }

    @Override
    /**
     * Returns String representation of the <code>Deadline</code> item.
     *
     * @return A String that represents the <code>Deadline</code> item, including its description, done status
     *         and deadline.
     */
    public String toString() {
        return String.format("[%s] ", this.letterType)
                + (this.isDone ? this.MARKED : this.UNMARKED) + " "
                + this.description
                + " (by: " + this.deadline + ")";
    }

    /**
     * Returns String representation of the <code>Deadline</code> object for data storage.
     * The format is as such: D|{done status}|{description}|{deadline}
     *
     * @return A String representing this <code>Deadline</code> object.
     */
    public String toSaveFormat() {
        return "D|" + (this.isDone ? 1 : 0) + "|" + this.description + "|" + this.deadline;
    }

    ;
}


