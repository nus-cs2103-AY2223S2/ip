package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event task with a from time and to time.
 */
public class Event extends Task {
    private String from;
    private String to;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    /**
     * Constructor method for Event.
     *
     * @param title title of the Event.
     * @param from  starting date-time of the event.
     * @param to    ending date-time of the event.
     */
    public Event(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
        this.type = "[E]";
        try {
            this.fromDate = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            this.toDate = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Overloaded constructor method for Event.
     * Primarily for when loading on startup.
     *
     * @param title title of the Event.
     * @param from  starting date-time of the Event.
     * @param to    ending date-time of the Event.
     * @param done  status of the Event.
     */
    public Event(String title, String from, String to, boolean done) {
        super(title);
        this.from = from;
        this.to = to;
        this.type = "[E]";
        this.done = done;
        try {
            this.fromDate = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            this.toDate = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Alternative toString method purely for writing to hard drive.
     * Can also be used for testing.
     *
     * @return String representation of the task with all attribute information.
     */
    @Override
    public String toData() {
        return this.type + " | " + (this.done ? "1" : "0") + " | " + this.title + " | " + this.from + "-" + this.to;
    }

    /**
     * Method for getting String representation of Deadline.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String toString() {
        return super.toString() + " (from: " + this.fromDate.format(DateTimeFormatter.ofPattern("HHmm MMM d yyyy"))
              + " to: " + this.toDate.format(DateTimeFormatter.ofPattern("HHmm MMM d yyyy")) + ")";
    }
}
