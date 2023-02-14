package task;
import task.Task;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Deadline class inherits from the Task class and is used for representing all
 * deadline tasks, which is defined with a deadline date and time.
 *
 * @author      Tseng Chen-Yu
 * @version     %I%, %G%
 * @since       1.0
 */
public class Deadline extends Task {
    private LocalDate deadlineDate;
    private Date deadlineTime;

    /**
     * Constructor for Deadline class
     *
     * @param description Description for the deadline task.
     * @param deadlineDate Deadline date for the deadline task.
     * @param deadlineTime Deadline time for the deadline task.
     * @param isDone Deadline task completion status.
     */
    public Deadline(String description, LocalDate deadlineDate, Date deadlineTime, boolean isDone) {
        super(description, isDone);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Retrieves the deadline date and time.
     *
     * @return Formatted deadline date and time, separated by a single space.
     *
     */
    public String getDeadline() {
        String formattedTime = new SimpleDateFormat("h:mm").format(deadlineTime);
        return deadlineDate + " " + formattedTime;
    }

    /**
     * Overrides the default toString method.
     *
     * @return A text UI representing a deadline entry, displaying the task type, completion status, description
     * and the deadline date and time.
     *
     */
    @Override
    public String toString() {
        String formattedDate = deadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String formattedTime = new SimpleDateFormat("h:mm a").format(deadlineTime);
        String deadlineCombined = formattedDate + " " + formattedTime;
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + deadlineCombined + ")";
    }
}
