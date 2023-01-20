package duke.task;

import duke.utils.LocalDateTimeUtils;

import java.time.LocalDateTime;

/**
 * Represents an event task.
 */
public class Event extends Task {
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Creates an Event object.
     *
     * @param isDone Is the event done.
     * @param description Description of the event.
     * @param start When the event starts.
     * @param end When the event ends.
     */
    public Event(boolean isDone, String description, LocalDateTime start, LocalDateTime end) {
        super(isDone, description);

        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String startStr = start.format(LocalDateTimeUtils.outputDateTimeFormatter);
        String endStr = end.format(LocalDateTimeUtils.outputDateTimeFormatter);

        return String.format("[E]%s (from: %s to: %s)", super.toString(), startStr, endStr);
    }
}
