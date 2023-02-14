package duke.task;

import java.time.LocalDateTime;

/**
 * The Deadline class is a type of task.
 *
 * @author Chia Jeremy
 */
public class Deadline extends Task {

    protected LocalDateTime dateTime;

    /**
     * Class constructor of a deadline task.
     *
     * @param description the description of the task
     * @param dateTime    the deadline to finish the task
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    /**
     * Returns the specific date and time of the deadline.
     *
     * @return the deadline to finish the task
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.dateTime.toLocalDate() + " " + this.dateTime.toLocalTime() + ")";
    }
}
