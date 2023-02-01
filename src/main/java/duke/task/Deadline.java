package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline: subclass of Task that tracks endDate
 *
 * author Guo-KeCheng
 */
public class Deadline extends Task {

    private LocalDate endDate;

    /**
     * Deadline class constructor
     * Default isCompleted to be false
     *
     * @param task Task description as String
     * @param endDate End date as String
     * @throws DateTimeParseException When endDate is in the wrong format
     */
    public Deadline(String task, String endDate) throws DateTimeParseException {
        super(task, false);
        this.endDate = LocalDate.parse(endDate);
    }


    /**
     * Deadline class constructor
     *
     * @param task Task description as String
     * @param isCompleted Completion status. True -> Completed. False -> Not Completed
     * @param endDate End date as String
     * @throws DateTimeParseException When endDate is in the wrong format
     */
    public Deadline(String task, boolean isCompleted, String endDate) throws DateTimeParseException {
        super(task, isCompleted);
        this.endDate = LocalDate.parse(endDate);
    }

    /**
     * Get the String representation of the task type
     *
     * @return String representation of the task type
     */
    @Override
    public String getTaskType() {
        return "Deadline";
    }

    /**
     * Get the String representation of the completion status
     *
     * @return String representation of the completion status
     */
    @Override
    public String getStatus() {
        return String.format("%s", isCompleted());
    }

    /**
     * Get the String representation of the task description
     *
     * @return String representation of the task description
     */
    @Override
    public String getDescription() {
        return getTask() + " | " + endDate;
    }

    /**
     * Override toString method
     *
     * @return String representation of task object including completion status
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
