package duke.tasks;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
        assert this.by != null : "LocalDate object not given";
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DTFORMAT) + ")";
    }

    @Override
    public boolean isToBeReminded(int days) {
        LocalDate now = LocalDate.now();
        long daysUntilTask = now.until(this.by, ChronoUnit.DAYS);
        return daysUntilTask < days && !isDone;
    }
}
