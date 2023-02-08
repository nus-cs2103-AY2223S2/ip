package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a type of Task.
 * All objects of class Event have a 'start' and 'end' field
 * to indicate when does the event start and end.
 */
public class Event extends Task {
    protected String start;
    protected String end;
    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * Constructs an Event task with the given name, start date, and end date.
     * @param description The description of the Event Task.
     * @param start The start date of the Event Task.
     * @param end The end date of the Event Task.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.startDate = LocalDate.parse(start);
        this.endDate = LocalDate.parse(end);

    }

    /**
     * The string representation of the Deadline object.
     * @return The name of this task and the details of the timing.
     */
    @Override
    public String toString() {
        String startDateString = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endDateString = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() +
                "(from: " + startDateString + " to: " + endDateString + ")";
    }
}
