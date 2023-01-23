package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 *
 * @author wz2k
 */
public class Event extends Task {
    /** End of event */
    private LocalDate to;

    /** Start of event */
    private LocalDate from;

    /**
     * Creates a deadline task.
     *
     * @param desc Description of the event task.
     * @param marked Task marking.
     * @param from Start of event.
     * @param to End of event.
     */
    public Event(String desc, boolean marked, String from, String to) {
        super(desc, marked);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Returns the task type, checkbox, description and timeline.
     *
     * @return Event task details.
     */
    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + this.from.format(pattern)
                + " to: " + this.to.format(pattern) + ")";
    }

    /**
     * Returns the string to store the event task in a file.
     *
     * @return File storage version of the event task.
     */
    @Override
    public String toTaskStorageString() {
        return "E" + "|" + super.toTaskStorageString() + "|"
                + this.from + "|" + this.to;
    }
}
