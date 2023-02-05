package duke;

import java.time.LocalDateTime;

/**
 * Represents a Task with a start and end time.
 */
public class Event extends Task {

    protected LocalDateTime startDateTime;
    protected LocalDateTime endDateTime;

    /**
     * Constructs an instance of Event with specified description, start and end time.
     *
     * @param description Task description.
     * @param startDateTime Start time of event.
     * @param endDateTime End time of event.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Constructs an instance of Event with specified task status, description, start and end time.
     *
     * @param isDone Status of the task (completed or not completed).
     * @param description Task description.
     * @param startDateTime Start time of event.
     * @param endDateTime End time of event.
     */
    public Event(boolean isDone, String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(isDone, description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Returns the start time of this Event.
     *
     * @return Start time of event.
     */
    LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    /**
     * Updates the start time of this Event with specified start time.
     *
     * @param startDateTime New start time of the event.
     */
    void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    /**
     * Return the end time of this Event.
     *
     * @return End time of the event.
     */
    LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Updates the end time of this Event with specified end time.
     *
     * @param endDateTime New end time of the event.
     */
    void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toCsv() {
        return "E," + super.toCsv() + ","
                + startDateTime + "," + endDateTime;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (From: "
                + startDateTime.getDayOfMonth() + " " + startDateTime.getMonth() + " " + startDateTime.getYear() + " "
                + startDateTime.getHour() + startDateTime.getMinute() // TODO: bugalert (may not always be 4-digits)
                + " To: "
                + endDateTime.getDayOfMonth() + " " + endDateTime.getMonth() + " " + endDateTime.getYear() + " "
                + endDateTime.getHour() + endDateTime.getMinute() // TODO: bugalert (may not always be 4-digits)
                + ")";
    }
}
