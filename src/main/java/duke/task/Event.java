package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents Event class
 */
public class Event extends Task {
    private LocalDate start;
    private LocalDate end;

    /**
     * Initializes a new Event object
     * @param type of task
     * @param detail of event
     * @param marked whether event is marked or not
     * @param start starting day of event
     * @param end ending day of event
     */
    public Event(String type, String detail, boolean marked, String start, String end) {
        super(type, detail, marked);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Initializes a new Event object
     * @param type of task
     * @param detail of event
     * @param start starting day of event
     * @param end ending day of event
     */
    public Event(String type, String detail, String start, String end) {
        super(type, detail);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }


    /**
     * Returns event printed out properly.
     *
     * @return event in full details.
     */
    @Override
    public String toString() {
        if (marked) {
            return "[E][X] " + super.detail + " (from: "
                    + this.start.format(DateTimeFormatter
                    .ofPattern("MMM dd yyyy"))
                    + " to: " + this.end.format(DateTimeFormatter
                    .ofPattern("MMM dd yyyy")) + ")";
        } else {
            return "[E][ ] " + super.detail + " (from: "
                    + this.start.format(DateTimeFormatter
                    .ofPattern("MMM dd yyyy"))
                    + " to: " + this.end.format(DateTimeFormatter
                    .ofPattern("MMM dd yyyy")) + ")";
        }
    }
}