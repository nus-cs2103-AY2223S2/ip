package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A deadline task.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate byDate;

    /**
     * Constructs a deadline task with given description and deadline.
     *
     * @param description Description of deadline task.
     * @param by Deadline of deadline task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDate = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
