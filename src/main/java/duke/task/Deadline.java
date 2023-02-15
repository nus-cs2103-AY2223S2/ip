package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.parser.DateTimeParser;
import duke.ui.Ui;

/**
 * Represents a Deadline, which is a type of Task that has to be done before a specific datetime.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    /**
     * Constructor for Deadline class that sets the description and deadline.
     *
     * @param input for deadline.
     * @throws DukeException if unable to set deadline.
     */
    public Deadline(String input) throws DukeException {
        super(input.split(" /by ")[0]);
        setDeadline(input);
    }

    /**
     * Constructor for Deadline class that sets the description, deadline, and status of deadline task.
     *
     * @param input for deadline.
     * @throws DukeException if unable to set deadline.
     */
    public Deadline(String input, String taskStatus) throws DukeException {
        super(input.split(" /by ")[0]);
        setDeadline(input);
        markTaskIfNeeded(taskStatus, this);
    }

    private void setDeadline(String description) throws DukeException {
        try {
            deadline = DateTimeParser.parse(description.split(" /by ")[1]);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            throw new DukeException("I'm sorry, but Fake Duke doesn't know what that means :-(");
        } catch (DateTimeParseException dtpe) {
            throw new DukeException("Invalid datetime format.\nPlease use yyyy-mm-dd HH:mm (E.g. 2019-10-15 18:00).");
        }
    }

    @Override
    public LocalDateTime getDate() {
        return deadline;
    }

    @Override
    public String getRawTask() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("D ~ %d ~ %s ~ %s\n", isDone ? 1 : 0, description, formatter.format(deadline));
    }

    /**
     * Returns the String representation of a Deadline.
     *
     * @return String representation of a Deadline in this format:
     *     [D][{status}] {description} (by: {deadline}).
     */
    @Override
    public String toString() {
        return String.format("[D][%c] %s\n(by: %s)%s", getStatusIcon(), description,
                Ui.getStringDateTime(deadline), super.getUrgentMessage(deadline));
    }
}
