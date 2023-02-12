package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;
import duke.enums.Views;

/**
 * Deadline, task with a date
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Creates a Deadline
     *
     * Will try and parse it as date and throw exception when it cannot
     *
     * @param title of the Task that that is being created
     * @param by    the deadline of the Task
     * @throws DukeException
     */
    public Deadline(String title, String by) throws DukeException {
        this(title, by, false);
    }

    /**
     * Creates a Deadline, with isDone field. Mostly used by Storage
     *
     * @param title  of the Task that that is being created
     * @param by     the deadline of the Task
     * @param isDone status of the Task
     * @throws DukeException
     */
    public Deadline(String title, String by, boolean isDone) throws DukeException {
        super(title, isDone);
        try {
            this.by = LocalDateTime.parse(by.replace("/by", "").trim());
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException(Views.DATE_PARSE_ERR_STRING.str());
        }
    }

    /**
     * Method for formatting the Task to store in a txt file
     *
     * @return String of the Task
     */
    @Override
    public String toExport() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Get a String representation to display to user of a Task
     *
     * @return String representation of the Task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}
