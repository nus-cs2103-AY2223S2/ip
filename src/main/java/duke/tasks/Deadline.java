package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of Task that stores a description and the time it should be done by
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for Deadline class
     *
     * @param description description of the task
     * @param by time the task should be completed by
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Another constructor for Deadline class
     *
     * @param description description of the task
     * @param by time the task should be completed by
     * @param isDone whether the task should be marked upon creation
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the string representation of Deadline
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

    /**
     * Returns the string representation of Deadline in data format
     *
     * @return string representation of Deadline in data format
     */
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

    /**
     * Checks if the given Object is the same as this
     *
     * @param o the Object being compared
     * @return true if o is an instance of this and have the same description and by
     */
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
