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

    @Override
    public String toString() {
        String from = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
        String to = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mm a"));
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
