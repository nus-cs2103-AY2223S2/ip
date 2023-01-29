package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * This class represents task of the type 'Event'.
 */
public class Event extends Tasks {
    private LocalDateTime from = null;
    private LocalDateTime to = null;

    /**
     * Construct a new Event instance with the given content, from_date and isDone state.
     * This method stores the string data as a LocalDateTime object.
     * @param content
     * @param from
     * @param isDone
     */
    public Event(String content, boolean isDone, String from, String to) {
        super(content, isDone);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.from = LocalDateTime.parse(from, format);
        this.to = LocalDateTime.parse(to, format);
        this.type = 'E';
    }

    /**
     * This method returns the string representation of the Event object.
     * @return String
     */
    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
        String from = this.from.format(format);
        String to = this.to.format(format);
        return "[" + this.getTypeIcon() + "]"
                + "[" + this.getStatusIcon() + "] " + this.seeTaskContent() + " (from: " + from + " to: " + to + ")";
    }
}
