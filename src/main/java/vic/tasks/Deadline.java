package vic.tasks;

import java.util.Date;

import vic.utilities.Parser;


/**
 * Represents Deadline task. A <code>Deadline</code> class corresponds to
 * the deadline task
 */
public class Deadline extends ITask {
    private final Date by;


    /**
     * Constructor for Deadline
     *
     * @param description for task
     * @param by          date
     */
    public Deadline(String description, Date by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for Deadline
     *
     * @param description for task
     * @param by          date
     * @param isDone      status
     */
    public Deadline(String description, Date by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toSaveFormat() {
        return "[D] " + "/by: " + by.getTime() + " /content: " + super.toString();
    }
    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        ITask task = (ITask) obj;
        return task.getId() == this.getId();
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + Parser.DATE_OUTPUT_FORMAT.format(this.by) + ")";
    }
}
