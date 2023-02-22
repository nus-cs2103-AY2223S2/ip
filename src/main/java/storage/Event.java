package storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Event task
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    /**
     * Creates a new Event with the specified description
     * @param description The specified description
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Custom string to represent an event for printing
     * @return Custom string
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                from.format(DateTimeFormatter.ofPattern("MMM dd yyyy")),
                to.format(DateTimeFormatter.ofPattern("MMM dd yyyy")));
    }

    /**
     * Custom string to represent an event data to be saved
     * @return Custom string
     */
    @Override
    public String toData() {
        return String.format("E | %s | %s | %s", super.toData(), from, to);
    }

    @Override
    public int compareTo(Task other) {
        if (other instanceof Event) {
            Event e = (Event) other;
            return this.from.compareTo(e.from);
        }
        return this.description.compareTo(other.description);
    }
}
