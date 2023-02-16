package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Event is a task that inherit Tasks, and it represents a task that has a certain start
 * date and time, and a certain end date and time
 */
public class Event extends Tasks {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * Returns an Event Object that inherits the Tasks Class, it is a task with a
     * certain start date and time, and a certain end date and time
     * @param description the details of the event
     * @param from the start date and time of the event
     * @param to the end date and time of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    /**
     * Overrides the toString() method, it prints out the full description
     * of the Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
            + this.from.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))
                + " to: " + this.to.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) + ")";
    }

    /**
     * Saves the event into data/duke.txt
     */
    @Override
    public String log() {
        return "E" + super.log() + " | " + this.from + " - " + this.to + "\n";
    }
}
