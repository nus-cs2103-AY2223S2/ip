package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class duke.Event is a subclass of duke.Task, encapsulate details
 * about a type of user's tasks which happen during a period
 * of time.
 *
 * @author hhchinh2002
 */
public class Event extends Task {
    // The starting time of the duke.Event
    private LocalDate start;
    //The ending time of the duke.Event
    private LocalDate end;

    /**
     * Creates an duke.Event task object with given description and starting, ending time
     *
     * @param description The description for the duke.Event task
     * @param start The starting time of the duke.Event
     * @param end The ending time fo the duke.Event
     */
    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public LocalDate getStartTime() {
        return this.start;
    }

    public LocalDate getEndTime() {
        return this.end;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event target = (Event) o;
            return target.getDescription().equals(this.getDescription())
                    && target.getStartTime().equals(this.getStartTime())
                    && target.getEndTime().equals(this.getEndTime());
        }
        return false;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
