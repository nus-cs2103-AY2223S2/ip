package duke.tasks;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exception.DukeException;

/**
 * Represents Task object with deadline type.
 */
public class Deadline extends Task {

    private final LocalDate deadline;

    /**
     * Creates a new Deadline object with parent constructor and date
     *
     * @param description Task description of the Deadline object
     * @param deadline Deadline date of the task
     * @throws DukeException if there is error parsing datetime
     */
    public Deadline(String description, String deadline) throws DukeException {
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Parse Error: " + e.getMessage() + "\n"
                    + "Accepted format: \"YYYY-MM-DD\"");
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
        if (isTaskDone()) {
            sb.append("1 | ");
        } else {
            sb.append("0 | ");
        }
        sb.append(this.description).append(" | ");
        sb.append(this.deadline).append("\n");
        return sb.toString();
    }

    /**
     * Returns string representation of the deadline task
     *
     * @return String representation of the deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.getMonth().toString().substring(0, 3) + " "
                + deadline.getDayOfMonth() + " "
                + deadline.getYear() + ")";
    }
}
