package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles Deadline object.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline object.
     *
     * @param description Description of Deadline.
     * @param by By Date/Time of event.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for Deadline object.
     *
     * @param description Description of Deadline.
     * @param marked Boolean of Deadline status.
     * @param by By Date/Time of event.
     */
    public Deadline(String description, boolean marked, LocalDate by) {
        super(description, marked);
        this.by = by;
    }

    /**
     * Return string of Deadline.
     *
     * @return Formatted Deadline with details as String.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
