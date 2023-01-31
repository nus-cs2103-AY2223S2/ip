package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

    protected final LocalDateTime from;
    protected final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String fromStr = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
        String toStr = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mma"));
        return "[E]" + super.toString() + " (from: " + fromStr + " to: " + toStr + ")";
    }
}
