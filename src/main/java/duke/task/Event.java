package duke.task;

import java.time.LocalDateTime;

import duke.utils.DateUtil;

/**
 * Event
 */
public class Event extends Task {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

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
        this.startDate = from;
        this.endDate = to;
    }

    /**
     * returns from date.
     * @return {@link LocalDateTime} object
     */
    public LocalDateTime getFrom() {
        return startDate;
    }
    /**
     * returns to date.
     * @return {@link LocalDateTime} object
     */
    public LocalDateTime getTo() {
        return endDate;
    }

    /**
     * {@inheritDoc}
     */
    public String toCsv() {
        return "E," + super.toCsv() + "," + DateUtil.dateToString(startDate) + "," + DateUtil.dateToString(endDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
            + DateUtil.dateToPrettyString(startDate)
            + " to: "
            + DateUtil.dateToPrettyString(endDate)
            + ")";
    }
}
