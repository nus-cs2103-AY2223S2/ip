import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Subclass of Task class used by Duke to keep track of user's tasks inputted.
 *
 * @author Bo Kuan (LG17)
 * @version CS2103T AY22/23 Semester 2
 */

public class Event extends Task {

    /**
     * A string representing the start of this Event instance.
     */
    protected LocalDateTime from;

    /**
     * A string representation of the end of this Event instance.
     */
    protected LocalDateTime to;

    /**
     * Constructor for an Event instance.
     *
     * @param description String describing this Deadline.
     *
     * @param from String representing the start of this Event.
     *
     * @param to String representing the end of this Event.
     */
    public Event(String description, String from, String to)
            throws DukeBadInstructionFormatException {
        super(description);

        try {
            this.from = Task.getLocalDateTime(from);
            this.to = Task.getLocalDateTime(to);
        } catch (DateTimeParseException e) {
            throw new DukeBadInstructionFormatException("Use date/time format: " +
                    Task.STORE_DATE_TIME_FORMAT);
        }
    }

    /**
     * Returns the string representation of an Event.
     *
     * @return The string representation of an Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + Task.getDateTimeString(this.from) +
                " to: " + Task.getDateTimeString(this.to) + ")";
    }
}
