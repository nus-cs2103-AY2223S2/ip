package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A subclass of a task: Event
 */
public class Event extends Task {
    protected LocalDateTime begin;
    protected LocalDateTime end;

    /**
     * Initializes a new Event
     * @param description description of event
     * @param begin date and time when the event starts
     * @param end date and time when the event ends
     * @param isDone whether the event is completed
     */
    public Event(String description, String begin, String end, Boolean isDone) {
        super(description, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.begin = LocalDateTime.parse(begin, formatter);
        this.end = LocalDateTime.parse(end, formatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:"
                + this.begin.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + " to:"
                + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy h:mm a")) + ")";
    }

    @Override
    public String parse() {
        return "E" + " | " + super.parse() +  " | "
                + this.begin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + " | "
                + this.end.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
