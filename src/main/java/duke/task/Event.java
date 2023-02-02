package duke.task;

import java.time.LocalDateTime;

/**
 * An Event object is a Task with a start time and end time.
 */
public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Constructs an unchecked Event with a title, start time and end time.
     * @param title The title of the Event task.
     * @param startTime A datetime string in ISO 8601 format.
     * @param endTime A datetime string in ISO 8601 format.
     */
    public Event(String title, String startTime, String endTime) {
        super(title);
        this.startTime = super.parseDateString(startTime);
        this.endTime = super.parseDateString(endTime);
    }

    /**
     * Constructs an Event with a title, start time and end time that is checked
     * or unchecked depending on the value of isDone.
     * @param title The title of the Event task.
     * @param startTime A datetime string in ISO 8601 format.
     * @param endTime A datetime string in ISO 8601 format.
     * @param isDone Boolean to indicate if the Deadline should be checked or not.
     */
    public Event(String title, String startTime, String endTime, boolean isDone) {
        super(title, isDone);
        this.startTime = super.parseDateString(startTime);
        this.endTime = super.parseDateString(endTime);
    }

    /**
     * Returns the disk format representation of the Event object.
     * @return Event object in disk format.
     */
    @Override
    public String toDiskFormat() {
        return String.format("E|%d|%s|%s|%s", super.getIsDone() ? 1 : 0, super.getTitle(),
                super.dateTimeToString(this.startTime), super.dateTimeToString(this.endTime));
    }

    /**
     * Returns a printable version of the Event object. It contains the title,
     * start time, end time and an indicator of whether it is checked or not.
     * @return  The string representation of this Event object.
     */
    @Override
    public String toString() {
        return String.format("[%s][E] %s (from: %s to %s)", super.getIsDone() ? "X" : " ",
                super.getTitle(), super.dateTimeToString(this.startTime),
                super.dateTimeToString(this.endTime));
    }
}
