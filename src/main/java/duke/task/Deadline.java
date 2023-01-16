package duke.task;

import java.time.LocalDateTime;

import duke.utils.DateUtil;

/**
 * Deadline
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * @see Task#Task()
     */
    public Deadline(String title, LocalDateTime by) {
        this(title, false, by);
    }

    /**
     * @see Task#Task(String, boolean)
     */
    public Deadline(String title, boolean isDone, LocalDateTime by) {
        super(title, isDone);
        this.by = by;
    }

    /**
     * returns by date.
     * @return {@link LocalDateTime} object
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * @see Task#toCsv()
     */
    public String toCsv() {
        return "D," + super.toCsv() + "," + DateUtil.dateToString(by) + ",";
    }

    /**
     * @see Task#toString()
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
        + DateUtil.dateToPrettyString(by)
        + ")";
    }
}
