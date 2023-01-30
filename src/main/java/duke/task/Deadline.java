package duke.task;

import duke.exception.InvalidDateTimeException;
import duke.helper.Parser;
import java.time.LocalDateTime;

/**
 * Subclass of task representing a deadline task
 */
public class Deadline extends Task {
    private LocalDateTime dueDateTime;

    public Deadline(String description, String by, boolean isDone) throws InvalidDateTimeException {
        super(description, isDone, "D");
        this.dueDateTime = Parser.handleDateTime(by);
    }

    /**
     * Returns the string representation of a deadline task
     *
     * @return string representation of a deadline task
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s %s)", super.toString(),
                dueDateTime.toLocalDate(), dueDateTime.toLocalTime());
    }
}
