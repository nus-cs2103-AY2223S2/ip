package duke.task;

import duke.storage.Storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class DeadlineTask extends Task {

    private static final String EVENT_SYMBOL = "D";
    private LocalDateTime endTime;

    /**
     * Creates a deadline task.
     * @param description The description of the task.
     * @param endTime The time of the deadline.
     */
    public DeadlineTask(String description, LocalDateTime endTime) {
        super(description, EVENT_SYMBOL);
        this.endTime = endTime;
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