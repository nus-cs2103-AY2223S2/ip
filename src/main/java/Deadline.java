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
    public Deadline(String desc, boolean marked, String by) {
        super(desc, marked);
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

    @Override
    public String toTaskStorageString() {
        return "D" + "|" + super.toTaskStorageString() + "|" + this.by;
    }
}
