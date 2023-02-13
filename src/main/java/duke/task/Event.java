package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeBadInstructionFormatException;
import duke.reminder.Reminder;
import duke.storage.Storage;
import duke.time.Time;

/**
 * Subclass of <code>Task</code> class used by duke.Duke to keep track of user's tasks inputted.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class Event extends Task {
    /**
     * A <code>LocalDateTime</code> representing the start of the <code>Event</code> instance.
     */
    private LocalDateTime from;
    /**
     * A <code>LocalDateTime</code> representing the end of the <code>Event</code> instance.
     */
    private LocalDateTime to;

    /**
     * Constructor for an <code>Event</code> instance.
     *
     * @param description String describing this <code>Event</code>.
     * @param from String representing the start of this <code>Event</code>.
     * @param to String representing the end of this <code>Event</code>.
     */
    public Event(String description, String from, String to)
            throws DukeBadInstructionFormatException {
        super(description);

        try {
            this.from = Time.getLocalDateTime(from);
            this.to = Time.getLocalDateTime(to);
            LocalDateTime remindDate = Time.getRemindDefaultLocalDateTime(from);
            this.reminder = new Reminder(remindDate, this);
        } catch (DateTimeParseException e) {
            throw new DukeBadInstructionFormatException("Use date/time format: "
                    + Time.STORE_DATE_TIME_FORMAT);
        }
    }
    /**
     * Returns the string representation of an <code>Event</code>.
     *
     * @return The string representation of an <code>Event</code>.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Time.getDisplayFormatDateTimeString(this.from)
                + " to: " + Time.getDisplayFormatDateTimeString(this.to) + ")";
    }
    /**
     * Returns the string representation of a <code>Event</code> for storage.
     *
     * @return The string representation of a <code>Event</code> for storage.
     */
    @Override
    public String getFileFormatString() {
        //to be split using "@"
        String s = Storage.SPLITTER;
        LocalDateTime remindDate = this.reminder.getLocalDateTime();
        String remindString = Time.getFileFormatDateTimeString(remindDate);
        String fromString = Time.getFileFormatDateTimeString(this.from);
        String toString = Time.getFileFormatDateTimeString(this.to);
        return Task.EVENT_FILE_FORMAT + s + this.isDone + s + this.description + s
                + fromString + s + toString + s + remindString;
    }
}
