package duke.task;

import java.time.LocalDateTime;

import duke.utils.DateUtil;

/**
 * Event
 *
 * @see Task
 */
public class Event extends Task {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructs an event.
     */
    public Event(String title, LocalDateTime from, LocalDateTime to) {
        this(title, false, from, to);
    }

    /**
     * Constructs an event with predefined done state.
     */
    public Event(String title, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(title, isDone);
        this.startDate = from;
        this.endDate = to;
    }

    /**
     * Returns from date.
     * @return start date
     * @see LocalDateTime
     */
    public LocalDateTime getFrom() {
        return startDate;
    }
    /**
     * Returns to date.
     * @return end date
     * @see LocalDateTime
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
        return "🎈 " + super.toString() + "\n   ↳ 📅 "
            + DateUtil.dateToPrettyString(startDate)
            + " -> "
            + DateUtil.dateToPrettyString(endDate)
            + ".";
    }
}
