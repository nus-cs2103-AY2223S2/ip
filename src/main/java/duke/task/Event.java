package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a event task.
 *
 * @author wz2k
 */
public class Event extends Task {
    /**
     * End of the event.
     */
    private LocalDate to;

    /**
     * Start of the event.
     */
    private LocalDate from;

    /**
     * Constructor for duke.task.Event class.
     *
     * @param desc description of the event task.
     */
    public Event(String desc, boolean marked, String from, String to) {
        super(desc, marked);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * This method returns the task type, checkbox, description and timeline.
     *
     * @return event task details.
     */
    @Override
    public String toString() {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString() + " (from: " + this.from.format(pattern)
                + " to: " + this.to.format(pattern) + ")";
    }

    @Override
    public String toTaskStorageString() {
        return "E" + "|" + super.toTaskStorageString() + "|"
                + this.from + "|" + this.to;
    }
}
