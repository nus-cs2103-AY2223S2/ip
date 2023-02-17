package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event task that has description of task, start date and end date
 */
public class Event extends TaskWithDate {
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor for Event
     * @param desc
     * @param from
     * @param to
     */
    public Event(String desc, LocalDateTime from, LocalDateTime to) {
        super(desc, "E", from);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets start date.
     * @return start date
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Gets end date.
     * @return end date
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Prints string representation of event task.
     * @return string representation of event task
     */
    public String toString() {
        return super.toString()
                + String.format("[from: %s to: %s]", this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")),
                        this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
    }
}
