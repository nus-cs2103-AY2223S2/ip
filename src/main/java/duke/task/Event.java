package duke.task;

import java.time.LocalDateTime;

import duke.util.DateTimeUtils;

/**
 * Event task representation.
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Creates an event task.
     *
     * @param description The description of the event.
     * @param from The starting date of the event.
     * @param to The ending date of the event.
     */
    public Event(String description, String place, LocalDateTime from, LocalDateTime to) {
        super(description, place, TaskType.EVENT);
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
