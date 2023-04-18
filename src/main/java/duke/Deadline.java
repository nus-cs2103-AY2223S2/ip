package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task that with a deadline.
 */
public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a new Deadline task.
     *
     * @param description Description of the task.
     * @return Deadline task.
     * @throws DateTimeParseException If the date is in the wrong format.
     */
    public static Deadline createDeadline(String description) throws DateTimeParseException {
        String[] split = description.split("/by ");
        LocalDate d1 = LocalDate.parse(split[1]);
        return new Deadline(split[0], d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    public void setBy(String newBy) {
        this.by = newBy;
    }

    /**
     * Overrides the default Object::toString
     *
     * @return String representation of a Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " " + description + "(by: " + by + ")";
    }
}
