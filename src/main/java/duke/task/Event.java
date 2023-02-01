package duke.task;

import java.time.LocalDateTime;

import duke.exception.InvalidDateTimeException;
import duke.helper.Parser;

/**
 * Subclass of tasks representing an event task
 */
public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime dueDateTime;

    /**
     * Constructor for the Event class
     *
     * @param description task's desc
     * @param from starting date and time of event
     * @param to ending date and time of event
     * @param isDone whether the task is done
     * @throws InvalidDateTimeException If the dateTime input is invalid
     */
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
