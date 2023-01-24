package duke.task;

import duke.util.DateTimeUtils;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description, TaskType.EVENT);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(DateTimeUtils.DATE_TIME_FORMAT_OUTPUT), to.format(DateTimeUtils.DATE_TIME_FORMAT_OUTPUT));
    }
}
