import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("dd MMM yyy HH:mm");

        return String.format("[E]%s (from: %s to: %s)", super.toString(), from.format(dateTimeFormat), to.format(dateTimeFormat));
    }
}
