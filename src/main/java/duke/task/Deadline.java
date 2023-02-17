package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class to support Deadline tasks.
 * Deadlines are tasks that need to be done before a specific date/time.
 */
public class Deadline extends Task {

    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    protected LocalDateTime by;

    /**
     * Constructor to create a Deadline task.
     *
     * @param description String containing Deadline description.
     * @param by String containing Deadline date.
     * @param tag String containing Deadline tag.
     */
    public Deadline(String description, String by, String tag) throws DukeException {
        super(description, tag);
        try {
            this.by = LocalDateTime.parse(by, inputFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Did you use the right date format (yyyy-mm-dd hh:mm)?");
        }
    }

    public Deadline(String description, String by) throws DukeException {
        this(description, by, null);
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
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString()
                + " (by: " + by.format(outputFormatter) + ")";
    }
}
