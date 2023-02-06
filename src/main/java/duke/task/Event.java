package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /** Start date and time of event */
    private LocalDateTime startDateTime;
    /** End date and time of event */
    private LocalDateTime endDateTime;

    /**
     * Constructs Event class.
     *
     * @param description Description of task.
     * @param startDateTime Start date and time of task.
     * @param endDateTime End date and time of task.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Gets start date and time of task.
     *
     * @return Start date and time of task.
     */
    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Gets end date and time of task.
     *
     * @return End date and time of task.
     */
    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Checks if event is upcoming.
     *
     * @return Status of event whether it is upcoming.
     */
    public boolean isUpcoming() {
        return LocalDateTime.now().isBefore(startDateTime);
    }

    /**
     * Checks if event is ongoing.
     *
     * @return Status of event whether it is ongoing.
     */
    public boolean isOngoing() {
        return LocalDateTime.now().isAfter(startDateTime) && LocalDateTime.now().isBefore(endDateTime);
    }

    /**
     * Checks if event has passed.
     *
     * @return Status of event whether it has passed.
     */
    public boolean isPassed() {
        return LocalDateTime.now().isAfter(endDateTime);
    }

    /**
     * {@inheritDoc}
     *
     * Includes type of task and its start and end dates.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String start = this.startDateTime.format(formatter);
        String end = this.endDateTime.format(formatter);
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
