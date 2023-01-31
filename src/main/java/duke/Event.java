package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    protected DateTimeFormatter timeFormat;

    public Event(String description, LocalDateTime from, LocalDateTime to, Boolean isDone) {
        super(description, 'E', isDone);
        this.from = from;
        this.to = to;
        this.timeFormat = DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm");
    }

    /**
     * Returns a string representation of this event task
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from:" + from.format(timeFormat) + " to:" + to.format(timeFormat) + ")";
    }

    /**
     * Returns a string representation of what is saved in the database
     * @return String
     */
    @Override
    public String savedAs() {
        return (super.savedAs() + "|" + this.from.format(timeFormat) + "|" + this.to.format(timeFormat));
    }
}