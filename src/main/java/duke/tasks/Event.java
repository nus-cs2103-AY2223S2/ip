package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.InvalidDateException;



/**
 * Event is a task that happens from a start date/time to an end period.
 */
public class Event extends Task {

    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("MMM dd yyyy");
    private final LocalDate start;
    private final LocalDate end;

    /**
     * Constructor for Event.
     *
     * @param command Description of the Event.
     * @param start The date/time when the Event is supposed to start.
     * @param end The date/time when the Event is supposed to end
     * @throws InvalidDateException If the date is invalid.
     */

    public Event(String command, String start, String end)
            throws InvalidDateException {
        super(command);
        try {
            this.start = LocalDate.parse(start);
            this.end = LocalDate.parse(end);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
    }

    /**
     * Returns the String representation of the Event.
     *
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(formatter)
                + " to: " + end.format(formatter) + ")";
    }


    /**
     * Returns the String representation of Event to be stored.
     *
     * @return The string storage representation of the Event.
     */
    @Override
    public String taskToData() {
        int done = isDone() ? 1 : 0;
        String task = getTask();
        return String.format("[E] | %d | %s | %s | %s",
                done,
                task,
                this.start,
                this.end);
    }

}
