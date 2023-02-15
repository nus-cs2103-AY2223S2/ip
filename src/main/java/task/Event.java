package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class that inherits from Task.
 */
public class Event extends Task {

    private static final DateTimeFormatter formatOfDate = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
    private final LocalDate startDate;
    private final LocalDate endDate;

    /**
     * Construct Event.
     *
     * @param name Description of the task.
     * @param startDate Date that the event starts.
     * @param endDate Date that the event ends.
     */
    public Event(String name, LocalDate startDate, LocalDate endDate) {
        super(name);
        this.startDate = startDate;
        this.endDate = endDate;
        assert endDate.isAfter(startDate) || endDate.isEqual(startDate) : "end date should not be before start date";
    }

    /**
     * Returns the details of the event task to be written in file.
     *
     * @return Details of event task.
     */
    public String toText() {
        return "E" + "|" + getNameOfTask() + "|" + (isDone() ? 1 : 0) + "|" + startDate + "|" + endDate;
    }

    /**
     * Returns the details of the event task to be output to user.
     *
     * @return Details of event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + formatOfDate.format(startDate) + " to: " + formatOfDate.format(endDate) + ")";
    }
}
