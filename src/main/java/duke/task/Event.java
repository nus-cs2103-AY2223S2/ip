package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.InvalidEventDateTimeException;


/**
 * Represents Event class
 */
public class Event extends Task {
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Initializes a new Event object
     * @param type of task
     * @param detail of event
     * @param marked whether event is marked or not
     * @param start starting day of event
     * @param end ending day of event
     */
    public Event(String type, String detail, boolean marked, String start, String end) throws
            InvalidEventDateTimeException {
        super(type, detail, marked);
        try {
            this.start = LocalDateTime.parse(start);
            this.end = LocalDateTime.parse(end);
        } catch (DateTimeParseException e) {
            throw new InvalidEventDateTimeException();
        }
    }

    /**
     * Initializes a new Event object
     * @param type of task
     * @param detail of event
     * @param start starting day of event
     * @param end ending day of event
     */
    public Event(String type, String detail, String start, String end) throws InvalidEventDateTimeException {
        super(type, detail);
        try {
            this.start = LocalDateTime.parse(start);
            this.end = LocalDateTime.parse(end);
        } catch (DateTimeParseException e) {
            throw new InvalidEventDateTimeException();
        }
    }


    /**
     * Returns event printed out properly.
     *
     * @return event in full details.
     */
    @Override
    public String toString() {
        if (marked) {
            return "[E][X] " + super.detail + " (from: "
                    + this.start.format(DateTimeFormatter
                    .ofPattern("MMM dd yyyy hh:mm a"))
                    + " to: " + this.end.format(DateTimeFormatter
                    .ofPattern("MMM dd yyyy hh:mm a")) + ")";
        } else {
            return "[E][ ] " + super.detail + " (from: "
                    + this.start.format(DateTimeFormatter
                    .ofPattern("MMM dd yyyy hh:mm a"))
                    + " to: " + this.end.format(DateTimeFormatter
                    .ofPattern("MMM dd yyyy hh:mm a")) + ")";
        }
    }
}
