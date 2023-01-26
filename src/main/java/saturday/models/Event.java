package saturday.models;

import java.time.temporal.TemporalAccessor;

import saturday.utilities.DateTimeParser;
/**
 * The Event class is a model class that represents an event. It extends the Task class and
 * provides additional functionality for handling the start and end times of the event.
 *
 * @author Titus Lowe
 * @version 0.1
 */
public class Event extends Task {
    private TemporalAccessor from;
    private TemporalAccessor to;

    /**
     * Constructs a new event with the specified description, start time and end time.
     *
     * @param description the description of the event
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = DateTimeParser.parseDate(from);
        this.to = DateTimeParser.parseDate(to);
    }

    /**
     * Returns the start time of the event.
     *
     * @return the start time of the event
     */
    public TemporalAccessor getFrom() {
        return this.from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the end time of the event
     */
    public TemporalAccessor getTo() {
        return this.to;
    }

    /**
     * Returns a string representation of the event. The format of the returned string is "[E]"
     * followed by the result of the toString() method from the superclass,
     * and the start and end time of the event in the format "(from: start_time to: end_time)".
     *
     * @return a string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + DateTimeParser.printDateTime(from) + " to: "
                + DateTimeParser.printDateTime(to) + ")";
    }
}
