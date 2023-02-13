package james.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The event class.
 * An event is a task that is listed on the taskList with a time range of /from /to.
 */
public class Event extends Task {
    /** Starting time in d/MM/yyyy HHmm format */
    protected LocalDateTime start;
    /** Ending time in d/MM/yyyy HHmm format */
    protected LocalDateTime end;
    /**
     * Constructs an Event object.
     *
     * @param description The task description.
     * @param start The start time of the event.
     * @param end   The end time of the event.
     */
    public Event(String description, String start, String end) {
        super(description);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.start = LocalDateTime.parse(start, format);
        this.end = LocalDateTime.parse(end, format);
    }

    @Override
    public String toString() {
        String startFormat =  this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        String endFormat = this.end.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[E]" + super.toString() +  " (from:" + startFormat + " to:" + endFormat + ")";
    }

    @Override
    public String toStoreString() {
        String startFormat =  this.start.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        String endFormat = this.end.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        return "E | " + super.toStoreString() + " | " + startFormat + " | " + endFormat;
    }
}





