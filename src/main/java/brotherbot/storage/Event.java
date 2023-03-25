package brotherbot.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    public LocalDateTime start;
    public LocalDateTime end;

    /**
     * Constructor to create an Event object.
     *
     * @param description Description of the task.
     * @param start Start date and time of the task.
     * @param end End date and time of the task.
     */
    public Event(String description, String start, String end) {
        super(description);
        super.type = "E";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        this.start = LocalDateTime.parse(start, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }

    /**
     * Prints type, status, description, start and end of Task.
     *
     * @return String representation of Event object.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "[" + super.type + "] " + "[" + this.getStatusIcon() + "] " + this.description + " From: " + this.start.format(formatter) + " To: " + this.end.format(formatter);
    }

}
