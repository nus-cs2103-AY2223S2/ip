package duke.task;

import duke.storage.Storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event Task.
 */
public class EventTask extends Task {

    private static final String EVENT_SYMBOL = "E";
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Creates an Event Task.
     * @param description The description of the task.
     * @param startTime The start time of the event.
     * @param endTime The ending time of the event.
     */
    public EventTask(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, EVENT_SYMBOL);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return super.toString() + " (from: " + startTime.format(formatter)
                + " to: " + endTime.format(formatter) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSaveString(Storage storage) {
        return super.toSaveString(storage) + "|" + storage.formatDateTime(startTime)
                + "|" + storage.formatDateTime(endTime);
    }
}