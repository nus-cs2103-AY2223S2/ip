package duke.task;

import java.time.LocalDateTime;

import duke.exception.InvalidDateTimeException;
import duke.helper.Parser;

/**
 * Subclass of task representing a deadline task
 */
public class Deadline extends Task {
    private String dueDateTime;

    /**
     * Constructor for Deadline Class
     *
     * @param description desc of the task
     * @param by task's deadline
     * @param isDone whether the task is done
     * @throws InvalidDateTimeException If the dateTime input is invalid
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone, "D");
        this.dueDateTime = by;
    }

    public Deadline(String description, String by) throws InvalidDateTimeException {
        super(description, false, "D");
        this.dueDateTime = Parser.handleDateTime(by).toString();
    }

    /**
     * Returns the string representation of a deadline task
     *
     * @return string representation of a deadline task
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), dueDateTime);
    }
}
