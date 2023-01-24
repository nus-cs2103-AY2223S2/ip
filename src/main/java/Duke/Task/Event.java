package Duke.Task;
import Duke.DateTime.DateTime;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String name, LocalDateTime from, LocalDateTime to) {
        super(name);
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
        String from = DateTime.getDateTimeString(this.from);
        String to = DateTime.getDateTimeString(this.to);
        if (super.getStatus()) {
            return String.format("[E][X] %s (from: %s to: %s)\n", super.getTaskName(), from, to);
        } else {
            return String.format("[E][ ] %s (from: %s to: %s)\n", super.getTaskName(), from, to);
        }
    }
}
