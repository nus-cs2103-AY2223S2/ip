package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.startDate = from;
        this.endDate = to;
    }

    public String getStartDate() {
        return this.startDate.toString();
    }

    public String getEndDate() {
        return this.endDate.toString();
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        String start = startDate.format(format);
        String end = endDate.format(format);
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
