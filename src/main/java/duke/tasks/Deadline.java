package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline task, encapsulating a deadline <code>localDate</code>.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructor for a Deadline task object.
     * @param description The name of the deadline object.
     * @param by The deadline date for this Deadline, in the format <code>yyyy-mm-dd</code>.
     * @throws DateTimeParseException
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Getter method for a Deadline object.
     * @return The deadline as a <code>LocalDate</code> object.
     */
    public LocalDate getDeadline() {
        return this.by;
    }

    /**
     * String representation of a Deadline task.
     * @return String in the format <code>[D] description (by: due date)</code>.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

