package angela.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import angela.storage.Storage;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {

    private static final String EVENT_SYMBOL = "D";
    private LocalDateTime endTime;

    /**
     * Creates a deadline task.
     *
     * @param description The description of the task.
     * @param endTime     The time of the deadline.
     */
    public DeadlineTask(String description, LocalDateTime endTime) {
        super(description, EVENT_SYMBOL);
        this.endTime = endTime;
    }

    /**
     * Returns true if the days to the deadline endTime from now is within the given days
     *      and the deadline is not marked as done, false otherwise.
     *
     * @param maxDays Number of days to the endTime from now in which this method should return true.
     * @return True if the days to the endTime of this deadline from now is within the given days
     *      and the deadline is not marked as done, false otherwise.
     */
    @Override
    public boolean isUpcoming(long maxDays) {
        boolean isEndTimeBeforeNow = LocalDateTime.now().compareTo(endTime) > 0;
        if (isDone || isEndTimeBeforeNow) {
            return false;
        }
        long fullDaysBetween = LocalDateTime.now().until(endTime, ChronoUnit.DAYS);
        return fullDaysBetween <= maxDays;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return super.toString() + " (by: " + endTime.format(formatter) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toSaveString(Storage storage) {
        return super.toSaveString(storage) + "|" + storage.formatDateTime(endTime);
    }
}
