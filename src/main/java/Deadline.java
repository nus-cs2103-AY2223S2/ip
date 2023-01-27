/**
 * The Deadline class is a type of task.
 *
 * @author Chia Jeremy
 */

public class Deadline extends Task {

    protected String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }
}
