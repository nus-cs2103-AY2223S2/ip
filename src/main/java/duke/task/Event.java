package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * The Event class extends the DatedTask class and represents an event with a start and end date.
 */
public class Event extends DatedTask {
    // Unique identifier for Serializer implementation: do not change var name
    private static final long serialVersionUID = 103;

    private LocalDate start;
    private LocalDate end;

    /**
     * Constructs an Event task with a start and end date.
     *
     * @param name  The name of the event.
     * @param start The date the event begins on.
     * @param end   The date the event ends on.
     * @throws DukeException If the dates are not formatted properly or the name is invalid.
     */
    public Event(String name, String start, String end) throws DukeException {
        super(name, end);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
            assert this.start.isBefore(this.end) : "Start Date should be before end date!";
        } catch (DateTimeParseException e) {
            throw new DukeException("The event command should be used like this:\n"
                    + "\tevent {name} /from {YYYY-MM-DD} /to {YYYY-MM-DD}");
        }
    }

    @Override
    public boolean hasDate() {
        return true;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        DateTimeFormatter daydm = DateTimeFormatter.ofPattern("E, dd MMM");
        DateTimeFormatter daydmyy = DateTimeFormatter.ofPattern("E, dd MMM uu");
        return "[E]" + super.toString() + " (from: " + start.format(daydm) + " to: " + end.format(daydmyy)
                + ")";
    }
}
