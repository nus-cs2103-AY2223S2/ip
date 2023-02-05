package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type event. An <code>Event</code> object corresponds to
 * a task that has a start time and an end time.
 */
public class Event extends Task {

    protected String start;
    protected LocalTime startTime = LocalTime.of(0,0);
    protected String end;
    protected LocalTime endTime = LocalTime.of(23,59);

    /**
     * Returns a Event object.
     *
     * @param description Description of the Event.
     * @param start Start date of the Event.
     * @param end End date of the Event.
     * @param remarks Remarks of the Event.
     * @return Event that starts at start 00:00 and ends at end 23:59.
     */
    public Event(String description, String start, String end, String remarks) {
        super(description, remarks);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM d yyyy");
        LocalDate s = LocalDate.parse(start);
        this.start = s.format(dtf);
        LocalDate e = LocalDate.parse(end);
        this.end = e.format(dtf);
    }

    /**
     * Returns a Event object.
     *
     * @param description Description of the Event.
     * @param start Start date of the Event.
     * @param end End date of the Event.
     * @param startTime Start time of the Event.
     * @param endTime End time of the Event.
     * @param remarks Remarks of the Event.
     * @return Event that starts at start 00:00 and ends at end 23:59.
     */
    public Event(String description, String start, String end, String startTime, String endTime, String remarks) {
        this(description, start, end, remarks);
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start + " " + startTime + " to: " + end + " " + endTime + ")";
    }
}