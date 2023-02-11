package duke.task;

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
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return "[E]" + super.getStatusIcon() + " (from: " + dateFormat.format(this.from) + " to: "
                + dateFormat.format(this.to) + ")";
    }

    @Override
    public String toRecord() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return "E|" + super.toRecord() + "|" + dateFormat.format(from) + "|" + dateFormat.format(to);
    }
}
