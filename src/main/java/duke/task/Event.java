package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event: subclass of Task that tracks startDate and endDate
 *
 * author Guo-KeCheng
 */
public class Event extends Task {
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Event class constructor
     * Default isCompleted to be false
     *
     * @param task Task description as String
     * @param startDate Start date as String
     * @param endDate End date as String
     * @throws DateTimeParseException When endDate is in the wrong format
     */
    public Event(String task, String startDate, String endDate) throws DateTimeParseException {
        super(task, false);
        this.endDate = LocalDate.parse(endDate);
        this.startDate = LocalDate.parse(startDate);
    }

    /**
     * Event class constructor
     *
     * @param task Task description as String
     * @param isCompleted Completion status. True -> Completed. False -> Not Completed
     * @param startDate Start date as String
     * @param endDate Start date as String
     * @throws DateTimeParseException When endDate is in the wrong format
     */
    public Event(String task, boolean isCompleted, String startDate, String endDate)
            throws DateTimeParseException {
        super(task, isCompleted);
        this.endDate = LocalDate.parse(endDate);
        this.startDate = LocalDate.parse(startDate);
    }

    /**
     * Get the String representation of the task type
     *
     * @return String representation of the task type
     */
    @Override
    public String getTaskType() {
        return "Event";
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
        return getTask() + " | " + startDate + " | " + endDate;
    }

    /**
     * Override toString method
     *
     * @return String representation of task object including completion status
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
