package kude.models;

import java.time.LocalDateTime;
import java.util.Optional;

import kude.DukeException;

/**
 * Representation of an Event
 */
public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Create a new Event
     * @param content Content of Event
     * @param from Starting timing of Event as a LocalDateTime
     * @param to Ending timing of Event as a LocalDateTime
     */
    public Event(String content, Optional<String> place, LocalDateTime from, LocalDateTime to) {
        super(content, place);
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
