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
        if(super.isDone == true) {
            marked = 1;
        }
        return "D / "
                + marked
                + " / "
                + super.description
                + " - "
                + this.by.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
    }
}
