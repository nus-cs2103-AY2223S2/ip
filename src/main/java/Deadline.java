/**
 * Represents a deadline task.
 *
 * @author wz2k
 */
public class Deadline extends Task {
    /**
     * Deadline for the task.
     */
    private String by;

    /**
     * Constructor for Deadline class.
     *
     * @param desc description of the deadline task.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.by = by;
    }

    /**
     * This method returns the task type, checkbox, description and deadline.
     *
     * @return deadline task details.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.by + ")";
    }
}
