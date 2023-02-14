package iris.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import iris.exception.DateTimeException;

/**
 * Represents an event with a start and end date
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructor for Event
     * @param name description of event
     * @param from the start date/time of the task in the format: {dd-MM-yyyy HHmm}. Eg. 20-02-2002 2000
     * @param to the end date/time of the task in the format: {dd-MM-yyyy HHmm}. Eg. 20-02-2002 2000
     * @throws DateTimeException when there is a problem parsing given dates.
     */
    public Event(String name, String from, String to) throws DateTimeException {
        super(name);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw new DateTimeException();
        }
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String storageFormat() {
        return String.join("|", "E", super.storageFormat(), dateString(from), dateString(to)) + "\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateString(from) + ", to: " + dateString(to) + ")";
    }
}
