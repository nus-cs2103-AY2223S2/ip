package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * a type of Task that stores the time it should be done by
 *
 * @author Wong Yong Xiang
 */
public class Deadline extends Task {

    protected LocalDate by;

    /** constructor for Deadline class
     *
     * @param description description of the task
     * @param by time the task should be completed by
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /** returns the string representation of Deadline
     *
     * @return string representation of Deadline class
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy"))
                + ")";
    }

    @Override
    public String toDataFormatString() {
        int marked = 0;
        if(super.isDone) {
            marked = 1;
        }
        return "D / "
                + marked
                + " / "
                + super.description
                + " - "
                + this.by.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Deadline)) {
            return false;
        }

        Deadline d = (Deadline) o;
        if (this.description.equals(d.description) && this.by.equals(d.by)) {
            return true;
        } else {
            return false;
        }
    }
}
