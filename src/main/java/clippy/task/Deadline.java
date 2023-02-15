package clippy.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task with a deadline.
 *
 * @author chunzkok
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a new Deadline instance.
     *
     * @param description The description of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the Deadline.
     * @return A string representation of the Deadline.
     */
    @Override
    public String toString() {
        // only show day of the week + day of the month + month
        return "[D]" + super.toString()
                + " (by: " + by.format(DateTimeFormatter.ofPattern("EEE dd MMM")) + ")";
    }

    /**
     * Returns a string representation of the Deadline in CSV form.
     * @return A string representation of the Deadline in CSV form.
     */
    @Override
    public String getCsvString() {
        return String.format("D,%s,%s", super.getCsvString(), this.by);
    }
}

