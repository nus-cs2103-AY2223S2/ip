package task;

/**
 * Represents a Deadline
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Constructor to initialize a deadline object
     *
     * @param by The date/time of the deadline
     * @param desc The title of the deadline
     */
    public Deadline(String by, String desc) {
        super(desc);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return The string representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
