package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Represents a Deadline task.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for a Deadline task.
     *
     * @param description of the task.
     * @param by the deadline of the task.
     * @throws DukeException if user inputs do not match expected format.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please input the date for deadlines in yyyy-mm-dd format!");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ") \n" + this.priority;
    }
}

