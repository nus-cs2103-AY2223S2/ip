package seedu.duke;

import java.time.LocalDate;

/**
 * Class for tasks that have a specific period of time.
 * it extends the Task class.
 * @param type task type.
 * @param fromDate the beginning date.
 * @param toDate the end date.
 */
public class Event extends Task{
    protected String type = "[ E ]";
    protected LocalDate fromDate;
    protected LocalDate toDate;

    /**
     * Constructs an Event task object and initializes the needed parameters.
     *
     * @param description description of the task.
     * @param fromDate the beginning date.
     * @param toDate the end date.
     */
    public Event(String description, String fromDate, String toDate) {
        super(description);
        this.fromDate = LocalDate.parse(fromDate);
        this.toDate = LocalDate.parse(toDate);
    }

    /**
     * Returns the string describing the task.
     *
     * @return description of task.
     */
    @Override
    public String toString() {
        return type + super.toString() + "from: " + this.fromDate + " to: " + this.toDate;
    }
}
