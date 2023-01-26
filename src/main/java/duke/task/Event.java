package duke.task;

import duke.exception.DukeBadInstructionFormatException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Subclass of duke.task.Task class used by duke.Duke to keep track of user's tasks inputted.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */

public class Event extends Task {

    /**
     * A string representing the start of this duke.task.Event instance.
     */
    private LocalDateTime from;

    /**
     * A string representation of the end of this duke.task.Event instance.
     */
    private LocalDateTime to;

    private String fromString;

    private String toString;

    /**
     * Constructor for an duke.task.Event instance.
     *
     * @param description String describing this duke.task.Deadline.
     *
     * @param from String representing the start of this duke.task.Event.
     *
     * @param to String representing the end of this duke.task.Event.
     */
    public Event(String description, String from, String to)
            throws DukeBadInstructionFormatException {
        super(description);

        try {
            this.fromString = from;
            this.toString = to;
            this.from = Task.getLocalDateTime(from);
            this.to = Task.getLocalDateTime(to);
        } catch (DateTimeParseException e) {
            throw new DukeBadInstructionFormatException("Use date/time format: " +
                    Task.STORE_DATE_TIME_FORMAT);
        }
    }

    /**
     * Returns the string representation of an duke.task.Event.
     *
     * @return The string representation of an duke.task.Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Task.getDateTimeString(this.from) +
                " to: " + Task.getDateTimeString(this.to) + ")";
    }

    @Override
    public String getFileFormatString() {
        //to be split using "|"
        return "E" + "@" + this.isDone + "@" + this.description + "@"
                + this.fromString + "@" + this.toString;
    }
}
