package duke.task;

import java.time.LocalDateTime;

import duke.utils.DateUtil;

/**
 * Deadline.
 *
 * @see Task
 */
public class Deadline extends Task {

    private LocalDateTime startDate;

    /**
     * Constructor.
     */
    public Deadline(String title, LocalDateTime startDate) {
        this(title, false, startDate);
    }

    /**
     * Constructor with predefined done state.
     */
    public Deadline(String title, boolean isDone, LocalDateTime startDate) {
        super(title, isDone);
        this.startDate = startDate;
    }

    /**
     * Returns by date.
     * @return start date
     * @see LocalDateTime
     */
    public LocalDateTime getBy() {
        return startDate;
    }

    /**
     * {@inheritDoc}
     */
    public String toCsv() {
        return "D," + super.toCsv() + "," + DateUtil.dateToString(startDate) + ",";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "⌛ " + super.toString() + "\n   ↳ 📅 "
            + DateUtil.dateToPrettyString(startDate)
            + ".";
    }
}
