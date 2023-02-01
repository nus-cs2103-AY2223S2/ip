package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event task that has description of task, start date and end date
 */
public class Event extends Task {
    LocalDate from;
    LocalDate to;

    /**
     * Constructor for Event
     * @param desc
     * @param from
     * @param to
     */
    public Event(String desc,LocalDate from, LocalDate to) {
        super(desc,"E");
        this.from = from;
        this.to = to;
    }

    /**
     * Gets start date.
     * @return start date
     */
    public LocalDate getFrom() {
        return from;
    }

    /**
     * Gets end date.
     * @return end date
     */
    public LocalDate getTo() {
        return to;
    }

    /**
     * Prints string representation of event task.
     * @return string representation of event task
     */
    public String toString() {
        return super.toString() +
                String.format("[from: %s to: %s]", this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                        this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
