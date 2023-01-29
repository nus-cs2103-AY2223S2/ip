import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class DeadLine is a subclass of Task, encapsulate details
 * about a type of user's tasks which has a deadline.
 *
 * @author hhchinh2002
 */
public class Deadline extends Task {
    //The deadline of the task
    private LocalDate by;

    /**
     * Creates a Deadline task with given description and deadline time
     *
     * @param description The description for the task
     * @param by The deadline of the task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getDeadline() {
        return this.by;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline target = (Deadline) o;
            return target.getDescription().equals(this.getDescription())
                    && target.getDeadline().equals(this.getDeadline());
        }
        return false;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
