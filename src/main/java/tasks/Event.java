package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Task that has a start time and an end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    /**
     * Constructs Event instance.
     * @param taskName Name of task.
     * @param from Task start time.
     * @param to Task end time.
     */
    public Event(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
        try {
            this.fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        } catch (DateTimeParseException err) {
            this.fromDateTime = null;
        }
        try {
            this.toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        } catch (DateTimeParseException err) {
            this.toDateTime = null;
        }
    }

    public String getFromTime() {
        if (this.fromDateTime != null) {
            return fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm")) + " hrs";
        } else {
            return this.from;
        }
    }

    public String getToTime() {
        if (this.toDateTime != null) {
            return toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm")) + " hrs";
        } else {
            return this.to;
        }
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatus() + "] " + super.toString().strip()
                + " (from: " + this.getFromTime() + " to: " + this.getToTime() + ")";
    }

    @Override
    public String toSaveString() {
        return "E" + "=" + super.getStatus() + "=" + super.toSaveString().strip()
                + "=" + this.getFromTime() + "=" + this.getToTime();
    }
}
