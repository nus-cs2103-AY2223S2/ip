package Duke.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.to = LocalDate.parse(to.trim());
        this.from = LocalDate.parse(from.trim());
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    @Override
    public String toSave() {
        if (super.isDone == true) {
            return String.format("E | 1 | %s | %s | %s\n", super.getDescription(), this.from, this.to);
        }
        return String.format("E | 0 | %s | %s | %s\n", super.getDescription(), this.from, this.to);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

}
