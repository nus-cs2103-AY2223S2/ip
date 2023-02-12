package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import duke.storage.Storage;

/**
 * Represents an Event Task.
 */
public class EventTask extends Task {

    private static final String EVENT_SYMBOL = "E";
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Creates an Event Task.
     *
     * @param description The description of the task.
     * @param startTime   The start time of the event.
     * @param endTime     The ending time of the event.
     */
    public EventTask(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, EVENT_SYMBOL);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns true if the days to this event's startTime from now is within the given days, false otherwise.
     *
     * @param maxDays Number of days to the startTime from now in which this method should return true.
     * @return True if the days to the startTime of this event from now is within the given days, false otherwise.
     */
    @Override
    public boolean isUpcoming(long maxDays) {
        long daysBetween = LocalDateTime.now().until(startTime, ChronoUnit.DAYS);
        return daysBetween <= maxDays && daysBetween >= 0;
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
