package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to support Deadline tasks.
 * Deadlines are tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, inputFormatter);
    }

    /**
     * Returns string representation of deadline date (in input format).
     * This method is to be used for internal use (not for output to user) such
     * as to save to storage.
     *
     * @return Deadline date formatted for subsequent input.
     */
    public String getBy() {
        return this.by.format(inputFormatter);
    }

    /**
     * @inheritDoc
     *
     * @return "D".
     */
    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() +
                " (by: " + by.format(outputFormatter) + ")";
    }
}
