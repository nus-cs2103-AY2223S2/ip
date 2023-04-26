package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class for deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for deadline task.
     * @param description the description of the task.
     * @param by the deadline of the task.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }
    @Override
    public boolean isDeadline() {
        return true;
    }
    @Override
    public LocalDate getDeadline() {
        return by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
