import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 *
 * @author wz2k
 */
public class Deadline extends Task {
    /**
     * Deadline for the task.
     */
    private LocalDate by;

    /**
     * Constructor for Deadline class.
     *
     * @param desc description of the deadline task.
     */
    public Deadline(String desc, boolean marked, String by) {
        super(desc, marked);
        this.by = LocalDate.parse(by);
    }

    /**
     * This method returns the task type, checkbox, description and deadline.
     *
     * @return deadline task details.
     */
    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + "(by: " + this.by.format(pattern) + ")";
    }

    @Override
    public String toTaskStorageString() {
        return "D" + "|" + super.toTaskStorageString() + "|" + this.by;
    }
}
