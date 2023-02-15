package duke.task;

import duke.TaskCreationException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task
 */
public class Event extends Task {
    private final LocalDate from;
    private final LocalDate to;

    /**
     * Makes an event
     *
     * @param desc desc of event
     * @param from from when
     * @param to   to when
     * @throws TaskCreationException date parsing error
     */
    public Event(String desc, String from, String to) throws TaskCreationException {
        super(desc);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw new TaskCreationException("Error parsing date");
        }
    }

    @Override
    protected String getType() {
        return "[E]";
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" (From %s to %s)", this.from, this.to);

    }
}
