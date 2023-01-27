package duke.task;

import java.time.LocalDateTime;

/**
 * The Event class is a type of task.
 *
 * @author Chia Jeremy
 */

public class Event extends Task {

    protected LocalDateTime startDt;
    protected LocalDateTime endDt;

    /**
     * Class constructor of an event task.
     *
     * @param description the description of the task
     * @param startDt     the starting date and time
     * @param endDt       the ending date and time
     */
    public Event(String description, LocalDateTime startDt, LocalDateTime endDt) {
        super(description);
        this.startDt = startDt;
        this.endDt = endDt;
    }

    /**
     * Returns the specific starting date and time of the task.
     *
     * @return the starting date and time
     */
    public LocalDateTime getStartDt() {
        return this.startDt;
    }

    /**
     * Returns the specific ending date and time of the task.
     *
     * @return the ending date and time
     */
    public LocalDateTime getEndDt() {
        return this.endDt;
    }

    /**
     * Returns the string representation of the event task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.startDt.toLocalDate() + " " + this.startDt.toLocalTime()
                + " to: " + this.endDt.toLocalDate() + " " + this.endDt.toLocalTime() + ")";
    }
}
