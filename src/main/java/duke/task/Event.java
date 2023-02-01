package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    LocalDate from;
    LocalDate to;

    public Event(String desc,LocalDate from, LocalDate to) {
        super(desc,"E");
        this.from = from;
        this.to = to;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public String toString() {
        return super.toString() +
                String.format("[from: %s to: %s]", this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                        this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
