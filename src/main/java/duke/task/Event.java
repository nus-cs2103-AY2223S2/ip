package duke.task;

import java.time.LocalDateTime;

/**
 *      File name: Event.java
 *      @author: Jerome Neo
 *      Description: Event class that inherits from the Task class.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    /**
     * Constructor for Event class.
     *
     * @param description of the task.
     * @param from the date in format dd/MM/yyyy HHmm.
     * @param to the date in format dd/MM/yyyy HHmm.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.fromDateTime = Task.convertDateTime(from);
        this.toDateTime = Task.convertDateTime(to);
    }

    /**
     * Returns a string of the date that the event starts.
     *
     * @return a string.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns a string of the date that the event ends.
     *
     * @return a string.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns a LocalDateTime object.
     * @return from date.
     */
    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    /**
     * Returns a LocalDateTime object.
     * @return to date.
     */
    public LocalDateTime getToDateTime() {
        return toDateTime;
    }

    /**
     * Returns a string representation of the Event object.
     *
     * @return a string.
     */
    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.getDescription()
            + " (from: " + dateTimeToString(fromDateTime) + " to: " + dateTimeToString(toDateTime) + ")";
    }
}
