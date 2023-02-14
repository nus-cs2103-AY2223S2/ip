package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * <h1>Deadline tasks with date</h1>
 * Assumes the deadline class is not done unless specified.
 * Parses a date in localdatetime and stores it as a string.
 * @author Muhammad Reyaaz
 * @version %I% %G%
 * @since 11
 *
 */

class Deadline extends Task {
    protected String by;

    /**
     * Creates an instance of Deadline with an empty description and end date.
     */
    Deadline() {}

    /**
     * Creates an instance of Deadline with a description and an end date.
     *
     * @param description The description of the task
     * @param by          The end date of the task
     */
    Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates an instance of Deadline with a description, an end date, and a boolean indicating if the task is done.
     *
     * @param description The description of the task
     * @param by          The end date of the task
     * @param isDone      A boolean indicating if the task is done
     */
    Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Parses the end date into a `LocalDate` object, if possible, and formats it into the format "MMM dd yyyy". If
     * the end date cannot be parsed into a `LocalDate` object, returns the end date as is.
     *
     * @return The formatted end date as a string
     */
    String localDateParser() {
        try {
            LocalDate date = LocalDate.parse(by);
            return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            return by;
        }
    }

    /**
     * Marks the Deadline task as done.
     *
     * @return A new instance of Deadline with the same description, end date, and isDone set to `true`
     */
    @Override
    Deadline markAsDone() {
        return new Deadline(getDescription(), by, true);
    }

    /**
     * Marks the Deadline task as undone.
     *
     * @return A new instance of Deadline with the same description, end date, and isDone set to `false`
     */
    @Override
    Deadline markAsUndone() {
        return new Deadline(getDescription(), by, false);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + localDateParser() + ")";
    }
}
