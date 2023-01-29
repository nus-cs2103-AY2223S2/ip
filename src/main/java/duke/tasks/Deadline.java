package duke.tasks;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline is a Task with a due date.
 */

public class Deadline extends Task {
    protected String by;
    LocalDate dateTime;

    /**
     * Constructor for deadline.
     *
     * @param description Description of Deadline.
     * @param by Due date of deadline.
     * @param isDone Completion status of Deadline.
     */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super(description, isDone);
        this.by = by;
        try {
            this.dateTime = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("\tPlease enter a date in e.g yyyy-mm-dd format!");
        }
    }

    /**
     * A method that converts the Deadline into its String representation.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + dateTime.format(formatter) + ")";
    }

    /**
     * Converts the Deadline into the String format required to be saved in the Storage.
     *
     * @return String formatted data of Deadline.
     */
    public String saveFormat() {
        return String.format("D | %s | %s", super.saveFormat(), this.by);
    }
}

