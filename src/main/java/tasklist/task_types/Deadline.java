package tasklist.task_types;

import java.io.Serializable;
import java.time.format.DateTimeParseException;

import utility.date.Date;

/**
 * Represents a <code>task</code> object that contains a string holding the
 * <code>deadline</code>.
 * 
 * 
 * @author Brian Quek
 */
public class Deadline extends Task implements Serializable {
    protected Date deadline;

    /**
     * Constructor for the Deadline object.
     */
    public Deadline(String name, String date) throws DateTimeParseException {
        super(name);
        this.deadline = new Date(date);
    }

    /**
     * @return a String containing the task type and deadline details.
     */
    @Override
    public String toString() {

        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.dateToString());
    }

    public String toTextString() {
        return String.format("D | %s | %s | %s", super.status ? "1" : "0", super.name, this.deadline.dateToFileText());
    }
}
