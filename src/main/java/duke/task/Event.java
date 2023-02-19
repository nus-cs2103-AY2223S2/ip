package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event class for events.
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Constructor for the Event class.
     * @param description the description of the Task.
     * @param from date and timing at which the Event starts.
     * @param to date and timing at which the Event ends.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Represents the Event as a String.
     * @return String representation of the Event.
     */
    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " "
                + super.getDescription() + " (from: " + this.from.format(
                        DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    /**
     * Adds a Event
     * @param details user input details for creating the Event.
     * @return the newly constructed Event object.
     */
    public static Event addEvent(String details) {
        String description = details.substring(0, details.indexOf(" /from"));
        String fromAndTo = details.substring(details.indexOf(" /from") + " /from".length() + 1);
        String fromString = fromAndTo.substring(0, fromAndTo.indexOf(" /to"));
        String toString = fromAndTo.substring(fromAndTo.indexOf(" /to") + " /to".length() + 1);

        LocalDateTime from = LocalDateTime.parse(fromString, FORMATTER);
        LocalDateTime to = LocalDateTime.parse(toString, FORMATTER);

        return new Event(description, from, to);
    }
}
