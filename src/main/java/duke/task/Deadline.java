package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.exception.DukeBadInstructionFormatException;

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
     * A <code>String</code> representing the deadline of the <code>Deadline</code> instance.
     */
    private String byString;

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
            this.byString = by;
            this.by = Task.getLocalDateTime(by);
        } catch (DateTimeParseException e) {
            throw new DukeBadInstructionFormatException("Use date/time format: "
                    + Task.STORE_DATE_TIME_FORMAT);
        }
    }
    /**
     * Returns the string representation of a <code>Deadline</code>.
     *
     * @return The string representation of a <code>Deadline</code>.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Task.getDateTimeString(this.by) + ")";
    }

    @Override
    public String getFileFormatString() {
        //to be split using "@"
        return "D" + "@" + this.isDone + "@" + this.description + "@" + this.byString;
    }
}
