package pix.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Deadline task containing the description and due date.
 */
public class Deadline extends Task {
    /** Date and time of deadline. */
    protected LocalDateTime dueDate;

    /**
     * Constructs a new Deadline.
     *
     * @param description Description of the deadline.
     * @param dueDate When deadline is due dueDate.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = LocalDateTime.parse(dueDate, formatInput);
    }

    /**
     * Get the date of the deadline.
     *
     * @return Date of the deadline.
     */
    public LocalDate getDate() {
        return this.dueDate.toLocalDate();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "\nDue: " + dueDate.format(formatOutput);
    }

    @Override
    public String toSave() {
        return "D /" + super.toSave() + " / " + dueDate.format(formatInput);
    }
}
