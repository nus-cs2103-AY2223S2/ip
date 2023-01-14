package duke.task;

import java.time.LocalDateTime;

import duke.utils.DateUtil;

/**
 * Deadline
 */
public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(String title, LocalDateTime by) {
        this(title, false, by);
    }

    public Deadline(String title, boolean isDone, LocalDateTime by) {
        super(title, isDone);
        this.by = by;
    }

    public LocalDateTime getBy() {
        return by;
    }

    public String toCsv() {
        return "D," + super.toCsv() + "," + DateUtil.dateToString(by) + ",";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " 
        + DateUtil.dateToString(by)
        + ")";
    }
}
