package task;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Task component representing an event task.
 */
public class Event extends Task {
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Event constructor.
     *
     * @param task a task string
     * @param startTime the starting time for event
     * @param endTime the ending time for event
     */
    public Event(String task, LocalDate startTime, LocalDate endTime) {
        super(task, false);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Event constructor.
     *
     * @param task a task string
     * @param startTime the starting time for event
     * @param endTime the ending time for event
     * @param isCompleted completion status
     */
    public Event(String task, LocalDate startTime, LocalDate endTime, boolean isCompleted) {
        super(task, isCompleted);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Calculates the amount of days to the event.
     *
     * @param date date to be queried
     * @return The days to event (positive signifies deadline in future).
     */
    public long daysToEvent(LocalDate date) {
        if (date.isBefore(this.startTime)) {
            return ChronoUnit.DAYS.between(date, this.startTime);
        } else if (date.isAfter(this.endTime)) {
            return ChronoUnit.DAYS.between(date, this.endTime);
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s %s (from: %s to: %s)", super.getFormattedStatus(), super.getTask(),
                super.getFormattedDate(this.startTime), super.getFormattedDate(this.endTime));
    }

    @Override
    public String toDataString() {
        return "E | " + (super.getIsCompleted() ? "1" : "0") + " | " + super.getTask() + " | " + this.startTime + " | "
                + this.endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Event)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Event event = (Event) o;

        if (!startTime.equals(event.startTime)) {
            return false;
        }
        return endTime.equals(event.endTime);
    }
}
