package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    /** String used to represent the deadline of task. */
    protected String by;

    /** String used to assign the name of the task. */
    protected String desc;

    /** Date object used to represent the deadline of task. */
    LocalDate byDate;

    /**
     * Constructor for the Deadline class.
     *
     * @param desc The name of the task.
     * @param by The deadline of the task.
     */
    public Deadline(String desc, String by) {
        super(desc);
        this.desc = desc;
        this.by = by;
        byDate = LocalDate.parse(by);
    }

    /**
     * Overloaded constructor for the Deadline class.
     *
     * @param desc The name of the task.
     * @param by The deadline of the task.
     * @param b The status of the task.
     */
    public Deadline(String desc, String by, boolean b) {
        super(desc, b);
        this.desc = desc;
        this.by = by;
        byDate = LocalDate.parse(by);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * {@inheritDoc}
     */
    public String asCSV() {
        if (super.isDone) {
            return "D,1" + desc + "," + by;
        } else {
            return "D,0" + desc + "," + by;
        }
    }
}
