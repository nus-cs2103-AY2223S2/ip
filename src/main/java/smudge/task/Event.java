package smudge.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class represents a task of event type
 */
public class Event extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * constructor method to create a event type task
     * @param description name of event
     * @param from the start date time of event
     * @param to end date time of event
     * @throws DateTimeParseException when the start or end date time of event is invalid
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) throws DateTimeParseException {
        super(description);
        this.from = from;
        this.to = to;

    }

    /**
     * getter method for starting date time of event
     * @return starting date time of event
     */
    public LocalDateTime getFrom() {
        return this.from;
    }
    /**
     * getter method for ending date time of event
     * @return ending date time of event
     */
    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * method to return event task in string
     * @return event task in string
     */
    @Override
    public String toString() {
        String fromDateFormat = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        String toDateFormat = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[E]" + super.toString() + " (from: " + fromDateFormat + " to: " + toDateFormat + ")";
    }
}