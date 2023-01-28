package pix.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Deadline task containing the description and due date.
 */
public class Deadline extends Task {
    /** Date and time of deadline. */
    protected LocalDateTime by;

    /**
     * Constructs a new Deadline.
     *
     * @param description Description of the deadline.
     * @param by When deadline is due by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, formatInput);
    }

    /**
     * Get the date of the deadline.
     *
     * @return Date of the deadline.
     */
    public LocalDate getDate() {
        return this.by.toLocalDate();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(formatOutput) + ")";
    }

    @Override
    public String toSave() {
        return "D /" + super.toSave() + " / " + by.format(formatInput);
    }
}
