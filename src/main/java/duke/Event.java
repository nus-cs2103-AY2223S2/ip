package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(String description, boolean isDone, LocalDate from, LocalDate to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+ ")";
    }

    public String toStorableString() {
        // E|0|return book|June 6th|June 7th
        return "E" + "," + (this.isDone() ? "1" : "0") + "," + this.getDescription()
                + "," + this.from.toString() + "," + this.to.toString();
    }
}