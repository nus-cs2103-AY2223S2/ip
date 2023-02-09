package james.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /** Starting time in d/MM/yyyy HHmm format */
    protected LocalDateTime start;
    /** Ending time in d/MM/yyyy HHmm format */
    protected LocalDateTime end;
    /**
     * Constructor for Event.
     *
     * @param description The task description.
     * @param start The start time of the event.
     */
    public Event(String description, String start) {
        super(description);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.start = LocalDateTime.parse(start, format);

    }

    @Override
    public String toString() {
        String startFormat =  this.start.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[E]" + super.toString() + " (from:" + startFormat + ")";
    }

    @Override
    public String toStoreString() {
        String startFormat =  this.start.format(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"));
        return "E | " + super.toStoreString() + " | " + startFormat + " | "+ "\n";
    }

}


