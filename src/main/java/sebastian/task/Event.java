package sebastian.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import sebastian.time.Duration;

/**
 * Class representing an event
 */
public class Event extends Task {

    private static String taskType = "E";

    private Duration duration;

    /**
     * Constructor
     * @param isCompleted whether the event is completed
     * @param taskDescription description of the event
     * @param duration the duration of the event
     */
    public Event(boolean isCompleted, String taskDescription, Duration duration) {
        super(taskDescription, isCompleted);
        this.duration = duration;
    }

    /**
     * Checked if the event starts on the same day as the provided date
     * @param ld a date
     * @return whether the event starts on the same day as the provided date
     */
    public boolean isOnSameDay(LocalDate ld) {
        return duration.isSameDay(ld);
    }

    /**
     * Update details of an event
     * @param description new event description
     * @param from new start time
     * @param to new end time
     */
    public void update(String description, LocalDateTime from, LocalDateTime to) {
        if (description != null) {
            super.update(description);
        }
        if (from != null) {
            this.duration.updateFromTime(from);
        }
        if (to != null) {
            this.duration.updateEndTime(to);
        }
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " " + this.duration.toString();
    }

    /**
     * Format the event into a suitable String representation to be written to the hard disk
     * @return the formatted String representation
     */
    @Override
    public String formatForSave() {
        return taskType + "<>" + super.formatForSave() + "<>" + this.duration.formatForSave();
    }
}
