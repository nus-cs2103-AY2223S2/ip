/**
 * Represents a Deadline task
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Creates a Deadline task object
     * @param description Describes the task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * String representation of deadline task
     * @return String representation of deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
