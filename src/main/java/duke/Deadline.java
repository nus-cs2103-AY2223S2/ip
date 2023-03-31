package duke;

/**
 * Class that can store a Deadline task, which has an additional "by" field.
 */
public class Deadline extends Task {
    protected Date by;

    /**
     * Constructor for a Deadline object.
     *
     * @param description Task description
     * @param by Task deadline
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String representation of the task that is parsable by the Duke Storage parser.
     *
     * @return A String object that is parsable by the Duke Storage parser.
     */
    @Override
    public String getFileRepresentation() {
        return "deadline|" + super.getFileRepresentation() + "|" + this.by.asFileDate();
    }

    /**
     * Returns a String representation of the task to be printed on the screen.
     *
     * @return String representation for a Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
}
