package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline.
     * @param description Name of deadline
     * @param by Time to complete by
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    /**
     * Constructor for Deadline.
     * @param description Name of deadline
     * @param isCompleted whether the task is completed
     * @param by Time to complete by
     */
    public Deadline(String description, boolean isCompleted, LocalDate by) {
        super(description, isCompleted);
        this.by = by;
    }

    /**
     * Gets deadline from a Deadline object.
     * @return LocalDate deadline
     */
    public LocalDate getDeadline() {
        return this.by;
    }
    @Override
    public String toString() {
        String byFormatted = this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        return "[D]" + super.toString() + " (by: " + byFormatted + ")";
    }
}