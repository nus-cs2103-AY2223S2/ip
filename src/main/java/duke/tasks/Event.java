package duke.tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Process Event to String to store in duke.txt
     * @return Processed String
     */
    public String toFile() {
        return "E|" + this.isDone + "|" + this.desc + "|" + this.start + "|" + this.end;
    }

    @Override
    public String toString() {
        String statusIcon = this.getStatusIcon();
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("eeee MMMM d HH:mm");
        String startDnT = start.format(customFormat);
        String endDnT = end.format(customFormat);
        return "[E][" + statusIcon + "] " + this.desc + "(from: " + startDnT + " to: " + endDnT + ")";
    }
}
