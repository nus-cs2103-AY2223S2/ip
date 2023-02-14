package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents user event task.
 */
public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    /**
     * Constructor of Event class.
     *
     * @param description description of Event task
     * @param from Start datetime of the event
     * @param to End datetime of the event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.startDate = from;
        this.endDate = to;
    }

    /**
     * Returns string representation of Start datetime.
     *
     * @return a string representation of Start datetime.
     */
    public String getStartDate() {
        return this.startDate.toString();
    }

    /**
     * Returns string representation of End datetime.
     *
     * @return a string representaiton of End datetime.
     */
    public String getEndDate() {
        return this.endDate.toString();
    }

    /**
     *  Returns string representation of the object.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        DateTimeFormatter startFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
        DateTimeFormatter endFormat = DateTimeFormatter.ofPattern("HH:mm");
        String start = startDate.format(startFormat);
        String end = endDate.format(endFormat);
        return "[E]" + super.toString() + " (from: " + start + " to: " + end + ")";
    }
}
