package kude.models;

import kude.DukeException;

import java.time.LocalDateTime;

/**
 * Representation of an Event
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String content, LocalDateTime from, LocalDateTime to) {
        super(content);
        if (to.isBefore(from)) {
            throw new DukeException("Event's `from` cannot be before `to`");
        }
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the starting date time
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Gets the ending date time
     */
    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), from, to);
    }
}
