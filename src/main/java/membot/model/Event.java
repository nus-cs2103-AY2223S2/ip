package membot.model;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import membot.utils.DateTimeParser;

/**
 * Represents a <code>Task</code> that contains a start dateTime and
 * an end dateTime of the task.
 */
public class Event extends Task {
    public static final String TAG = "[E]";

    private String start;
    private String end;

    /**
     * Generates a <code>Event</code> object.
     *
     * @param title The title of the task to be completed.
     * @param start The start dateTime of the task.
     * @param end The end dateTime of the task.
     * @throws DateTimeException If the end dateTime is earlier than the start dateTime.
     */
    public Event(String title, String start, String end) throws DateTimeException {
        super(title);

        try {
            LocalDateTime d1 = DateTimeParser.parse(start);
            LocalDateTime d2 = DateTimeParser.parse(end);

            if (d2.isBefore(d1)) {
                throw new DateTimeException("/to time cannot be before /from time");
            }
            this.start = DateTimeParser.format(d1);
            this.end = DateTimeParser.format(d2);
        } catch (DateTimeParseException e) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * Returns the <code>Task</code> type.
     *
     * @return The <code>Task</code> type.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    /**
     * Returns <code>"~"</code> to signify that there is no deadline attached
     * to any <code>Event</code> task.
     *
     * @return <code>"~"</code>.
     */
    @Override
    public String getDeadline() {
        return Task.EMPTY;
    }

    /**
     * Returns the start dateTime attached to the <code>Event</code> task.
     *
     * @return The start dateTime attached to the <code>Event</code> task.
     */
    @Override
    public String getStartDateTime() {
        return this.start;
    }

    /**
     * Returns the end dateTime attached to the <code>Event</code> task.
     *
     * @return The end dateTime attached to the <code>Event</code> task.
     */
    @Override
    public String getEndDateTime() {
        return this.end;
    }

    /**
     * Returns a <code>String</code> representation of the <code>Event</code> task.
     *
     * @return A <code>String</code> representation of the <code>Event</code> task.
     */
    @Override
    public String toString() {
        return Event.TAG + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
