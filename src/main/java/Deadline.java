import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A type of task with a single deadline.
 */
public class Deadline extends Task {
    // Deadline for the task.
    protected LocalDateTime by;

    /**
     * Constructor for the deadline class.
     *
     * @param description Description of the deadline task.
     * @param by Deadline for the task.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, formatter);

    }

    /**
     * Overrides the toString method of the Task class.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a")) + ")";
    }

    /**
     * Overrides the printData method of the Task class.
     *
     * @return String representation of the deadline task in data form.
     */
    @Override
    public String printData() {
        return "D" + "/" + (isDone ? "1" : "0") + "/" + description + "/" + by;
    }
}
