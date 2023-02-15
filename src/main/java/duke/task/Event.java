package duke.task;

import java.time.LocalDateTime;

/**
 * The Event class is a type of task.
 *
 * @author Chia Jeremy
 */
public class Event extends Task {

    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    /**
     * Class constructor of an event task.
     *
     * @param description   the description of the task
     * @param startDateTime the starting date and time
     * @param endDateTime   the ending date and time
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns the specific starting date and time of the task.
     *
     * @return the starting date and time
     */
    public LocalDateTime getStartDt() {
        return this.startDateTime;
    }

    /**
     * Returns the specific ending date and time of the task.
     *
     * @return the ending date and time
     */
    public LocalDateTime getEndDt() {
        return this.endDateTime;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return super.toString()
                + " (from: " + this.startDateTime.toLocalDate() + " " + this.startDateTime.toLocalTime()
                + " to: " + this.endDateTime.toLocalDate() + " " + this.endDateTime.toLocalTime() + ")";
    }
}
