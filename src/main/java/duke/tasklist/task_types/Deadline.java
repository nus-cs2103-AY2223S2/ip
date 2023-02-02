package duke.tasklist.task_types;

import java.time.format.DateTimeParseException;

import duke.duke_exception.DukeException;
import duke.utility.date.Date;

/**
 * Represents a <code>task</code> object that contains a string holding the <code>deadline</code>.
 * 
 * @author Brian Quek
 */
public class Deadline extends Task {
    private static DukeException invalidDate =
            new DukeException("Invalid date input/format. [Format: yyyy-mm-dd] ");
    protected Date deadline;

    /**
     * Constructor for the Deadline object.
     */
    public Deadline(String name, String date) throws DukeException {
        super(name);
        try {
            this.deadline = new Date(date);
        } catch (DateTimeParseException e) {
            throw invalidDate;
        }

    }

    /**
     * @return a String containing the task type and deadline details.
     */
    @Override
    public String toString() {

        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.dateToString());
    }

}
