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
     * @param description Description of the event task.
     * @param isMarked Task marking.
     * @param from Start of event.
     * @param to End of event.
     */
    public Event(String description, boolean isMarked, String from, String to) {
        super(description, isMarked);
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
        return "[E]" + super.toString() + " (from: " + from.format(pattern)
                + " to: " + to.format(pattern) + ")";
    }

    /**
     * Returns the string to store the event task in a file.
     *
     * @return File storage version of the event task.
     */
    @Override
    public String toTaskStorageString() {
        return "E" + "|" + super.toTaskStorageString() + "|" + from + "|" + to;
    }
}
