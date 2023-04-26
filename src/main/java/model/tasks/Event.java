package model.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Representation of the Event task
 */
public class Event extends Task {
    protected DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm");
    protected LocalDateTime from;
    protected LocalDateTime to;
    private final String icon = "[E]";

    /**
     * Constructor for Event
     * @param description The description of the Event
     * @param from The start time of the Event
     * @param to The end time of the Event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Constructor for Event
     * @param description The description of the Event
     * @param from The start time of the Event
     * @param to The end time of the Event
     * @param status The status of the Event
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, Boolean status) {
        super(description);
        setIsComplete(status);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the Event to be saved
     * @return String
     */
    @Override
    public String savedAs() {
        String formattedTimeFrom = this.from.format(timeFormat.withResolverStyle(ResolverStyle.STRICT));
        String formattedTimeTo = this.to.format(timeFormat.withResolverStyle(ResolverStyle.STRICT));
        return String.format("E|%s|%s|%s|%s", getIsComplete(), getTaskDesc(), formattedTimeFrom, formattedTimeTo);
    }

    /**
     * Returns a string representation of this Event task
     * @return String
     */
    @Override
    public String toString() {
        String formattedTimeFrom = this.from.format(timeFormat.withResolverStyle(ResolverStyle.STRICT));
        String formattedTimeTo = this.to.format(timeFormat.withResolverStyle(ResolverStyle.STRICT));
        return String.format("%s%s (from: %s to: %s)", this.icon, super.toString(), formattedTimeFrom, formattedTimeTo);
    }
}
