package dudu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task class for deadline
 */
public class Deadline extends Task {
    private final String desc;
    private boolean isDone;
    private LocalDate by;

    /**
     * Constructor for deadline task.
     * @param desc Description of the task.
     * @param by The deadline date of the task.
     */
    public Deadline(String desc, String by) {
        super(desc, false);
        this.desc = desc;
        this.by = LocalDate.parse(by);
    }

    /**
     * Constructor for deadline task, with completion option.
     * @param desc Description of the task.
     * @param by The deadline date of the task.
     * @param isDone The completion status of the task.
     */
    public Deadline(String desc, String by, boolean isDone) {
        super(desc, isDone);
        this.desc = desc;
        this.by = LocalDate.parse(by);
    }

    @Override
    public String getType() {
        return "Deadline";
    }

    @Override
    public String getStatus() {
        return isDone() ? "1" : "0";
    }

    @Override
    public String getDescription() {
        return desc + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
