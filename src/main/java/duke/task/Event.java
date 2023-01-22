package duke.task;

import java.time.LocalDateTime;

import duke.utils.DateUtil;

/**
 * Event
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructor
     */
    public Event(String title, LocalDateTime from, LocalDateTime to) {
        this(title, false, from, to);
    }

    /**
     * Constructor with predefined done state
     */
    public Event(String title, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(title, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * returns from date.
     * @return {@link LocalDateTime} object
     */
    public LocalDateTime getFrom() {
        return from;
    }
    /**
     * returns to date.
     * @return {@link LocalDateTime} object
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * {@inheritDoc}
     */
    public String toCsv() {
        return "E," + super.toCsv() + "," + DateUtil.dateToString(from) + "," + DateUtil.dateToString(to);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
            + DateUtil.dateToPrettyString(from)
            + " to: "
            + DateUtil.dateToPrettyString(to)
            + ")";
    }
}
