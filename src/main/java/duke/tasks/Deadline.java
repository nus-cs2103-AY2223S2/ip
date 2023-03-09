package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Deadline is a Task with a due date.
 */

public class Deadline extends Task {
    private String by;
    private LocalDate dateTime;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * A Constructor for Deadline.
     * @param description Description of Deadline.
     * @param by Due date of Deadline.
     * @param isDone Completetion status of Deadline.
     * @throws DukeException if duke input format cannot be parsed by LocalDate.
     */

    public Deadline(String description, String by, boolean isDone) throws DukeException {
        super(description, isDone);
        this.by = by;
        try {
            this.dateTime = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("\tPlease enter a date in e.g yyyy-mm-dd format!");
        }
    }

    public LocalDate getDeadline() {
        return this.dateTime;
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

