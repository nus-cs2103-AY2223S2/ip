package Duke.Task;
import Duke.DateTime.DateTime;

import java.time.LocalDateTime;

/**
 * Task to represent Event task created by user
 */
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

    /**
     * @return String version of Event
     */
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
