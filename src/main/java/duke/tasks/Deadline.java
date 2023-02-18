package duke.tasks;

import java.time.LocalDate;

/**
 * This is the Deadline task class to represent deadlines passed to Duke.
 * Encapsulates all information related to the deadline, including the date of the deadline.
 */

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Creates a new Deadline object
     * @param description String description of the deadline represented.
     * @param by LocalDate object representing the date of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DTFORMAT) + ")";
    }
}
