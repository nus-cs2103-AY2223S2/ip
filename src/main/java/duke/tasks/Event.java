package duke.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class for Event Objects
 */
public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructor for Events
     * @param description task for the event
     * @param start start time for the event
     * @param end end time for the event
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Processes Event to String to store in duke.txt
     * @return Processed String
     */
    public String toFile() {
        return "E|" + this.isDone + "|" + this.desc + "|" + this.start + "|" + this.end;
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("eeee MMMM d u HH:mm");
        String startDnT = start.format(customFormat);
        String endDnT = end.format(customFormat);
        return "[E][" + statusIcon + "] " + this.desc + "(From: " + startDnT + " By: " + endDnT + ")";
    }
}
