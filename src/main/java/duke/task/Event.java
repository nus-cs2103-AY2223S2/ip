package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task.
 */
public class Event extends Task {
    /** The start date of the event. */
    protected LocalDate start;

    /** The end date of the event. */
    protected LocalDate end;

    /**
     * Constructor for Event.
     * 
     * @param description the description of the event.
     * @param start the start date of the event.
     * @param end the end date of the event.
     * @param isDone whether the event is done.
     */
    public Event(String description, LocalDate start, LocalDate end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Formats the event for user output.
     * 
     * @return the string representation of the event for user output.
     */
    @Override
    public String toString() {
        return "[E] [" + this.getStatusIcon() + "] " + this.getDescription() + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Formats the event for file output.
     * 
     * @return the string representation of the event for file output.
     */
    public String toFile() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.start + " | " + this.end;
    }
}
