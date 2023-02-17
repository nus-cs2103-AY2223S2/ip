package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class to support Event tasks.
 * Events are tasks that start at a specific date/time and end at a specific
 * date/time.
 */
public class Event extends Task {

    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor to create an Event task.
     *
     * @param description String containing Event description.
     * @param from String containing Event begin date.
     * @param to String containing Event end date.
     * @param tag String containing Event tag
     */
    public Event(String description, String from, String to, String tag) throws DukeException {
        super(description, tag);
        try {
            this.from = LocalDateTime.parse(from, INPUT_FORMATTER);
            this.to = LocalDateTime.parse(to, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeException("Did you use the right date format (yyyy-mm-dd hh:mm)?");
        }
    }

    public Event(String description, String from, String to) throws DukeException {
        this(description, from, to, null);
    }

    /**
     * Returns string representation of event begin date (in input format).
     * This method is to be used for internal use (not for output to user) such
     * as to save to storage.
     *
     * @return Event begin date formatted for subsequent input.
     */
    public String getFrom() {
        return this.from.format(INPUT_FORMATTER);
    }

    /**
     * Returns string representation of event end date (in input format).
     * This method is to be used for internal use (not for output to user) such
     * as to save to storage.
     *
     * @return Event end date formatted for subsequent input.
     */
    public String getTo() {
        return this.to.format(INPUT_FORMATTER);
    }

    /**
     * @inheritDoc
     *
     * @return "E".
     */
    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatusIcon() + "] " + super.toString()
                + " (from: " + from.format(OUTPUT_FORMATTER) + " to: "
                + to.format(OUTPUT_FORMATTER) + ")";
    }
}
