package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * This is the class for Deadline action
 * @author yanlinglim
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDateTime start;

    /**
     * This is the constructor for Deadline
     * @param description description of deadline
     * @param by the date where the dateline is due
     */
    public Deadline(String description, String by) {
        super(description);
        by = by.replaceAll("by ","");
        by = by.replaceAll("/","");
        this.by = by;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.start = LocalDateTime.parse(this.by, formatter);
        this.description = description;
        Task.actions += 1;
    }

    /**
     * Returns a string representation of the object.
     * @return String string representation of the object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + by + ")";
    }

    /**
     * Returns a string representation of the object to be placed in the list
     * @return String string representation of the object to be placed in the list
     */
    @Override
    public String toSaveString() {
        String timeStr = this.start.format(DateTimeFormatter.ofPattern("HH:mm, MMM dd yyyy"));
        return String.format("deadline || %s || %s || %s", super.toSaveString(), this.description, timeStr);
    }

    /**
     * Prints out the line to show that a deadline task has been added
     */
    public void toPrintDeadlineString() {
        System.out.println("Got it. I've added this task:");
        System.out.println(this);
        System.out.println("Now you have " + Task.actions + " tasks in the list");
    }

    /**
     * Adds deadline into tasklist
     */
    public void handleDeadline() {
        this.toPrintDeadlineString();
        Task.tasks.add(this);
    }
}
