package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 *
 * @author wz2k
 */
public class Deadline extends Task {
    /** Deadline of the task */
    private LocalDate by;

    /**
     * Creates a deadline task.
     *
     * @param desc Description of the deadline task.
     * @param marked Task marking.
     * @param by Task deadline.
     */
    public Deadline(String desc, boolean marked, String by) {
        super(desc, marked);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the task type, checkbox, description and deadline.
     *
     * @return Deadline task details.
     */
    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + this.by.format(pattern) + ")";
    }

    /**
     * Returns the string to store the deadline task in a file.
     *
     * @return File storage version of the deadline task.
     */
    @Override
    public String toTaskStorageString() {
        return "D" + "|" + super.toTaskStorageString() + "|" + this.by;
    }
}
