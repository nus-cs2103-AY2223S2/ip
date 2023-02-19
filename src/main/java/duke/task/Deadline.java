package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class for deadline Tasks.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for the Deadline class.
     * @param description the description of the Task.
     * @param by the date and timing at which the Deadline must be completed by.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Represents the Deadline as a String.
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + " " + super.getDescription() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    /**
     * Adds a Deadline
     * @param details user input details for creating the Deadline.
     * @return the newly constructed Deadline object.
     */
    public static Deadline addDeadline(String details) {
        String description = details.substring(0, details.indexOf(" /by"));
        String byString = details.substring(details.indexOf(" /by") + " /by".length() + 1);

        LocalDateTime by = LocalDateTime.parse(byString, FORMATTER);

        return new Deadline(description, by);
    }
}
