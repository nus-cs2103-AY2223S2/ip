package kude.models;

import kude.DukeException;

import java.time.LocalDateTime;

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

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), from, to);
    }
}
