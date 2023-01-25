package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline
 */
public class Deadline extends Task {

    protected LocalDate by;
    private static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");

    /**
     * Constructor to initialize a deadline object
     *
     * @param by The date/time of the deadline
     * @param desc The title of the deadline
     */
    public Deadline(LocalDate by, String desc) {
        super(desc);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return The string representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(FORMATTER) + ")";
    }
}
