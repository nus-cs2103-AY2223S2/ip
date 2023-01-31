package duke.task;

import java.time.format.DateTimeFormatter;

/**
 * Represents Event task with a description from when to when.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toTXT() {
        return String.format("E | %s | %s | %s", super.toTXT(), from, to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
