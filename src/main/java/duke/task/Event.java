package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * Represents task of the type 'Event'.
 */
public class Event extends Task {
    private LocalDateTime from = null;
    private LocalDateTime to = null;

    /**
     * Constructs a new Event instance with the given content, from_date and isDone state and
     * stores the string data as a LocalDateTime object.
     * @param content The content of the event.
     * @param isDone Whether the task is completed.
     * @param from The starting time of the event.
     * @param to The ending time of the event.
     */
    public Event(String content, boolean isDone, String from, String to) {
        super(content, isDone);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.from = LocalDateTime.parse(from, format);
        this.to = LocalDateTime.parse(to, format);
        this.type = type.E;
        this.time = this.to;
    }

    /**
     * Returns the string representation of the Event object.
     * @return String The string representation of the Event object.
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
        String from = this.from.format(format);
        String to = this.to.format(format);
        return "[" + this.getTypeIcon() + "]"
                + "[" + this.getStatusIcon() + "] " + this.getTaskContent() + " (from: " + from + " to: " + to + ")";
    }
}
