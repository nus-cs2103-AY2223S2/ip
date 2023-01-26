package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A subclass of Task that represents
 * a task with a deadline.
 *
 * @author Oskar Lew
 * @version 0.1
 * @since 0.1
 */
public class Deadline extends Task {
    protected LocalDateTime by;
    protected DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yyyy h:mm a");

    /**
     * Constructor of Deadline.
     *
     * @param description Description of the task.
     * @param by Time limit of the task.
     */
    public Deadline(String description, String by) throws DateTimeParseException {
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_DATE_FORMAT);
    }

    /**
     * Returns a string representation of Deadline
     * @return String
     */
    public String toString() {
        return "[D]" + getStatusIcon() + " " + description + " (by: " + by.format(OUTPUT_DATE_FORMAT) + ")";
    }
}
