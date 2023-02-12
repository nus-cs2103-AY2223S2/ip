package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This is the Event task class to represent events passed to Duke.
 * Encapsulates all information related to the event, including start and end dates.
 */
public class Event extends Task {
    protected LocalDate from;
    static DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected LocalDate to;

    /***
     * Creates a new Event object.
     * @param description String description of the event represented.
     * @param from LocalDate object representing the start date of the Event.
     * @param to LocalDate object representing the end date of the Event
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(format) + " to: " + to.format(format) + ")";
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    String getTypeIcon() {
        return "[E]";
    }
}
