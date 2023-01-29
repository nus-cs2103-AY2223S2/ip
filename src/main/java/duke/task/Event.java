package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    @Override
    public String encode() {
        return "event"
                + " " + this.description
                + " " + this.isDone
                + " " + this.from
                + " " + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}