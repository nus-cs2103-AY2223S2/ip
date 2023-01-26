package task_types;

/**
 * Represents a <code>task</code> object that contains a string holding the
 * <code>deadline</code>.
 * 
 * 
 * @author Brian Quek
 */
public class Deadline extends Task {
    protected String deadline;

    /**
     * Constructor for the Deadline object.
     */
    public Deadline(String name, String date) {
        super(name);
        this.deadline = date;
    }

    /**
     * @return a String containing the task type and deadline details.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }

    public String toTextString() {
        return String.format("D | %s | %s | %s", super.status ? "1" : "0", super.name, this.deadline);
    }
}
