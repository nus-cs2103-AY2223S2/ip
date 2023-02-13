package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeBadInstructionFormatException;
import duke.reminder.Reminder;
import duke.storage.Storage;
import duke.time.Time;

/**
 * Subclass of <code>Task</code> class used by <code>Duke</code> to keep track of user's
 * <code>Task</code>s inputted.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */
public class Deadline extends Task {
    /**
     * A <code>LocalDateTime</code> representing the deadline of the <code>Deadline</code> instance.
     */
    private LocalDateTime by;

    /**
     * Constructor for a <code>Deadline</code> instance.
     *
     * @param description String describing this <code>Deadline</code>.
     *
     * @param by deadline of this <code>Deadline</code>.
     */
    public Deadline(String description, String by) throws DukeBadInstructionFormatException {
        super(description);

        try {
            this.by = Time.getLocalDateTime(by);
            LocalDateTime remindDate = Time.getRemindDefaultLocalDateTime(by);
            this.reminder = new Reminder(remindDate, this);
        } catch (DateTimeParseException e) {
            throw new DukeBadInstructionFormatException("Use date/time format: "
                    + Time.STORE_DATE_TIME_FORMAT);
        }
    }
    /**
     * Returns the string representation of a <code>Deadline</code>.
     *
     * @return The string representation of a <code>Deadline</code>.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Time.getDisplayFormatDateTimeString(this.by) + ")";
    }

    @Override
    public String getFileFormatString() {
        //to be split using "@"
        String s = Storage.SPLITTER;
        LocalDateTime remindDate = this.reminder.getLocalDateTime();
        String remindString = Time.getFileFormatDateTimeString(remindDate);
        String byString = Time.getFileFormatDateTimeString(this.by);
        return Task.DEADLINE_FILE_FORMAT + s + this.isDone + s + this.description
                + s + byString + s + remindString;
    }
}
