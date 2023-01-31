package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) throws DateTimeParseException {
        super(description);
        this.from = from;
        this.to = to;

    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        String fromDateFormat = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        String toDateFormat = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[E]" + super.toString() + " (from: " + fromDateFormat + " to: " + toDateFormat + ")";
    }
}