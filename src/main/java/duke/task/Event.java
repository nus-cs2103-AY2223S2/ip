package duke.task;

import java.time.LocalDateTime;

/**
 * Represents an event - a task with specific start and end
 * dates and times.
 */
public class Event extends Task {

    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);

        assert from != null;
        assert to != null;

        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to, Priority priority) {
        super(description, priority);

        assert from != null;
        assert to != null;

        this.from = from;
        this.to = to;
    }

    /**
     * Gets the String representation of the event to be stored in the text file.
     *
     * @return The String representation of the event to be stored in the text file.
     */
    @Override
    public String getFileRepresentation() {
        String mark = (super.isDone) ? "X" : " ";

        return "E" + "~"
                + this.priority + "~"
                + mark + "~"
                + this.description + "~"
                + this.from + "~"
                + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + Task.getDateTimeString(this.from)
                + " ; to: " + Task.getDateTimeString(this.to)
                + ")";
    }
}
