package duke.task;

import duke.exception.InvalidDateTimeException;
import duke.helper.Parser;

/**
 * Subclass of tasks representing an event task
 */
public class Event extends Task {
    private String startDateTime;
    private String dueDateTime;

    /**
     * Constructor for the Event class
     *
     * @param description task's desc
     * @param from starting date and time of event
     * @param to ending date and time of event
     * @param isDone whether the task is done
     * @throws InvalidDateTimeException If the dateTime input is invalid
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone, "E");

        this.startDateTime = from;
        this.dueDateTime = to;
    }

    public Event(String description, String from, String to) throws InvalidDateTimeException {
        super(description, false, "E");

        startDateTime = Parser.handleDateTime(from).toString();
        dueDateTime = Parser.handleDateTime(to).toString();
    }

    /**
     * Returns the string representation of an event task
     *
     * @return string representation of an event task
     */
    @Override
    public String toString() {
        return String.format("%s (from: %s to: %s)", super.toString(),
                startDateTime, dueDateTime);
    }
}
