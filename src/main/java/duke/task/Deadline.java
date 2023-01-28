package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class: Instantiate when user creates a deadline
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
     * {@inheritDoc}
     */
    @Override
    public String getTaskType() {
        return "Deadline";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStatus() {
        return String.format("%s", isCompleted());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return getTask() + " | " + endDate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
