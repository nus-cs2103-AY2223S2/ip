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
    protected LocalDateTime deadline;

    /**
     * Constructor for Deadline class that sets the description and deadline.
     *
     * @param description Description of deadline.
     * @throws DukeException Throws exception if unable to set deadline.
     */
    public Deadline(String description) throws DukeException {
        super(description.split(" /by ")[0]);
        setDeadline(description);
    }

    /**
     * Constructor for Deadline class that sets the description, deadline, and status of deadline task.
     *
     * @param description Description of deadline.
     * @throws DukeException Throws exception if unable to set deadline.
     */
    public Deadline(String description, String taskStatus) throws DukeException {
        super(description.split(" /by ")[0]);
        setDeadline(description);
        markTaskIfNeeded(taskStatus, this);
    }

    @Override
    public LocalDateTime getDate() {
        return deadline;
    }

    /**
     * Returns the String representation of a Deadline.
     *
     * @return String representation of a Deadline in this format:
     *     [D][{status}] {description} (by: {deadline}).
     */
    @Override
    public String toString() {
        return String.format("[D][%c] %s\n(by: %s) %s", getStatusIcon(), description,
                Ui.getStringDateTime(deadline), super.getUrgentMessage(deadline));
    }

    private void setDeadline(String description) throws DukeException {
        try {
            deadline = DateTimeParser.parse(description.split(" /by ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("I'm sorry, but Fake Duke doesn't know what that means :-(");
        } catch (DateTimeParseException dtpe) {
            throw new DukeException("Invalid datetime format.\nPlease use yyyy-mm-dd HH:mm (E.g. 2019-10-15 18:00).");
        }
    }

    /**
     * Returns the raw String representation of a Deadline to be stored in the local file for storage.
     *
     * @return Raw String representation of a Task in this format: D ~ {status} ~ {description} ~ {deadline}.
     */
    @Override
    public String getRawTask() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return String.format("D ~ %d ~ %s ~ %s\n", isDone ? 1 : 0, description, dtf.format(deadline));
    }
}
