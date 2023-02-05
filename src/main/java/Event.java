import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFromDate() {
        return this.from.toString();
    }

    public String getToDate() {
        return this.to.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String start = from.format(format);
        String end = to.format(format);
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
