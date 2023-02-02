package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that has a description,
 * start date-time and end date-time.
 */
public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d LLL uuuu, hh:mm a");

    /**
     * Creates an event object with the given task description,
     * start date-time and end date-time.
     *
     * @param description The description of the task.
     * @param startDateTime The starting date-time of the event.
     * @param endDateTime The ending date-time of the event.
     */
    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }


    /**
     * Returns a string representation of the event task.
     *
     * @return A string representation of the event task,
     * with the format "[E][X] <Task description>" if it is done,
     * and "[E][ ] <Task description> otherwise".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startDateTime.format(formatter)
                + " to: " + endDateTime.format(formatter) + ")";
    }

    @Override
    public String getTaskState() {
        return "E | " + super.getTaskString()
                + " | " + startDateTime + " | " + endDateTime;
    }
}
