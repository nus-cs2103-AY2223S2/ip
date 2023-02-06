import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
        this.startDate = from;
        this.endDate = to;
    }

    public String getFromDate() {
        return this.from.toString();
        return this.startDate.toString();
    }

    public String getToDate() {
        return this.to.toString();
        return this.endDate.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String start = from.format(format);
        String end = to.format(format);
        String start = startDate.format(format);
        String end = endDate.format(format);
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
