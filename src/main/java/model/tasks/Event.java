package model.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * Representation of the Event task
 */
public class Event extends Task {
    private final String icon = "[E]";
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, Boolean status) {
        super(description);
        setIsComplete(status);
        this.from = from;
        this.to = to;
    }

    @Override
    public String savedAs() {
        String formattedTimeFrom = this.from.format(timeFormat.withResolverStyle(ResolverStyle.STRICT));
        String formattedTimeTo = this.to.format(timeFormat.withResolverStyle(ResolverStyle.STRICT));
        return String.format("T|%s|%s|%s|%s", getIsComplete(), formattedTimeFrom, formattedTimeTo, getTaskDesc());
    }

    /**
     * Returns a string representation of this To-Do task
     * @return String
     */
    @Override
    public String toString() {
        String formattedTimeFrom = this.from.format(timeFormat.withResolverStyle(ResolverStyle.STRICT));
        String formattedTimeTo = this.to.format(timeFormat.withResolverStyle(ResolverStyle.STRICT));
        return String.format("%s%s (from: %s to: %s)", this.icon, super.toString(), formattedTimeFrom, formattedTimeTo);
    }
}
