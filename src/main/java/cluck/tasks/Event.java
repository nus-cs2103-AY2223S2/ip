package cluck.tasks;

import java.time.LocalDateTime;

/**
 * Event is a Task with a start date-time and end date-time.
 */
public class Event extends Task {
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Instantiates a new Event.
     *
     * @param description event description
     * @param startTime   start date-time of event
     * @param endTime     end date-time of event
     */
    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = interpretLocalDateTime(startTime);
        this.endTime = interpretLocalDateTime(endTime);
    }

    /**
     * Instantiates a new Event.
     *
     * @param isMarked    Boolean that indicates whether event is marked.
     * @param description Event description.
     * @param startTime   Event start date time.
     * @param endTime     Event end date time.
     */
    public Event(boolean isMarked, String description, String startTime, String endTime) {
        super(isMarked, description);
        this.startTime = interpretLocalDateTime(startTime);
        this.endTime = interpretLocalDateTime(endTime);
    }

    @Override
    public String makeSaveFormat() {
        return "E" + super.makeSaveFormat() + "|" + this.startTime.format(DEFAULT_FORMATTER)
                + "|" + this.endTime.format(DEFAULT_FORMATTER) + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.startTime.format(DEFAULT_FORMATTER)
                + " to: " + this.endTime.format(DEFAULT_FORMATTER) + ")";

    }
}
