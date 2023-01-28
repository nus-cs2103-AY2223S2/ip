
/**
 * Contains information of a deadline
 * Contains description and deadline of the deadline
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Creates a deadline object
     *
     * @param description The description of the deadline
     * @param by Deadline time of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns type of task, completion status, description, and deadline of
     * the deadline
     *
     * @return Type of task, completion status, description, and deadline of
     * the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by + ")";
    }
}
