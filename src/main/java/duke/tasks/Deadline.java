package duke.tasks;

import exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * Represents Task object with deadline type.
 */
public class Deadline extends Task {

    private final LocalDate by;

    /**
     * Creates a new Deadline object with parent constructor and date
     *
     * @param description Task description of the Deadline object
     * @param by Deadline date of the task
     * @throws DukeException if there is error parsing datetime
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Parse Error: " + e.getMessage() + "\n" +
                    "\tAccepted format: \"YYYY-MM-DD\"");
        }
    }

    /**
     * Generates a data string representation of the Deadline object. It will be used
     * to store the Deadline object in storage file.
     *
     * @return Data string of the task
     */
    @Override
    public String getData() {
        StringBuilder sb = new StringBuilder();
        sb.append("D | ");
        if (this.isDone) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }
        sb.append(this.description).append(" | ");
        sb.append(this.by).append("\n");
        return sb.toString();
    }

    /**
     * Returns string representation of the deadline task
     *
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.getMonth().toString().substring(0, 3) + " " +
                by.getDayOfMonth() + " " +
                by.getYear() + ")";
    }
}
