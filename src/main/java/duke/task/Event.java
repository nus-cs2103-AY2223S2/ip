package duke.task;

import duke.exception.InvalidDateTimeException;
import duke.helper.Parser;

import java.time.LocalDateTime;

/**
 * Subclass of tasks representing an event task
 */
public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime dueDateTime;

    public Event(String description, String from, String to, boolean isDone) throws InvalidDateTimeException {
        super(description, isDone, "E");

        startDateTime = Parser.handleDateTime(from);
        dueDateTime = Parser.handleDateTime(to);
    }

    /**
     * Returns the string representation of an event task
     *
     * @return string representation of an event task
     */
    @Override
    public String toString() {
        return String.format("%s (from: %s %s to: %s %s)", super.toString(),
                startDateTime.toLocalDate(), startDateTime.toLocalTime(),
                dueDateTime.toLocalDate(), startDateTime.toLocalTime());
    }
}
