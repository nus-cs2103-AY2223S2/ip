package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Class for deadline object.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    /**
     * Constructor for deadline object.
     * Input of string deadline is parsed as a LocalDate object.
     * String deadline must be in YYYY-MM-DD format or exception is thrown.
     *
     * @param taskName The name of the task.
     * @param deadline The date of the deadline of task, must be in YYYY-MM-DD format.
     */
    public Deadline(String taskName, String deadline) throws DukeException {
        super(taskName);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("Invalid date format, please input as YYYY-MM-DD");
        }
    }

    /**
     * Returns string representation of the deadline task and the status of it.
     * Deadline format is represented as MMM dd yyyy.
     *
     * @return String representation of the task and the marked status.
     */
    @Override
    public String toString() {
        DateTimeFormatter newFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + "(by: " + this.deadline.format(newFormat) + ")";
    }
}
