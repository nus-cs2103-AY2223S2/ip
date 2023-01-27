package duke;

import java.time.LocalDateTime;

/**
 * Represents a Task with a start and end time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructs an instance of Event with specified description, start and end time.
     *
     * @param description Task description.
     * @param from Start time of event.
     * @param to End time of event.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructs an instance of Event with specified task status, description, start and end time.
     *
     * @param isDone Status of the task (completed or not completed).
     * @param description Task description.
     * @param from Start time of event.
     * @param to End time of event.
     */
    public Event(boolean isDone, String description, LocalDateTime from, LocalDateTime to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of this Event.
     *
     * @return Start time of event.
     */
    LocalDateTime getFrom() {
        return from;
    }

    /**
     * Updates the start time of this Event with specified start time.
     *
     * @param from New start time of the event.
     */
    void setFrom(LocalDateTime from) {
        this.from = from;
    }

    /**
     * Return the end time of this Event.
     *
     * @return End time of the event.
     */
    LocalDateTime getTo() {
        return to;
    }

    /**
     * Updates the end time of this Event with specified end time.
     *
     * @param to New end time of the event.
     */
    void setTo(LocalDateTime to) {
        this.to = to;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toCsv() {
        return "E," + super.toCsv() + ","
                + from + "," + to;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (From: "
                + from.getDayOfMonth() + " " + from.getMonth() + " " + from.getYear() + " "
                + from.getHour() + from.getMinute() // TODO: bugalert (may not always be 4-digits)
                + " To: "
                + to.getDayOfMonth() + " " + to.getMonth() + " " + to.getYear() + " "
                + to.getHour() + to.getMinute() // TODO: bugalert (may not always be 4-digits)
                + ")";
    }
}
