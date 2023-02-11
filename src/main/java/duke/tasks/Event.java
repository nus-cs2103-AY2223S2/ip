package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 *
 * @author Samarth Verma
 */
public class Event extends Task {

    private String desc;
    private LocalDate from;
    private LocalDate to;

    /**
     * Creates a new Event.
     *
     * @param id          The id of the event.
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(int id, String description, String from, String to) {
        super(id);
        desc = description;
        // TODO: Handle invalid dates.
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String description() {
        return desc;
    }

    @Override
    public String toString() {
        String isDone = isCompleted() ? "X" : " ";
        return String.format("[E][%s] %s (from: %s to %s)", isDone, description(),
                from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String serialize() {
        String isDone = isCompleted() ? "1" : "0";
        return String.format("E|%s|%s|%s|%s|%s", id(), isDone, description(), from, to);
    }

    /**
     * Deserializes an event from a string.
     *
     * @param s The string to deserialize from.
     * @return The deserialized event.
     */
    public static Event deserialize(String s) {
        String[] parts = s.split("\\|");
        Event event = new Event(Integer.parseInt(parts[1]), parts[3], parts[4], parts[5]);
        if (parts[2].equals("1")) {
            event.markCompleted();
        }
        return event;
    }
}
