package duke.Tasks;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(int id, String description, LocalDateTime start, LocalDateTime end) {
        super(id, description);
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
        String startDnT = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(start);
        String endDnT = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(end);
        return id + ". [E][" + statusIcon + "] " + this.desc + "(from: " + startDnT + " to: " + endDnT + ")";
    }
}
