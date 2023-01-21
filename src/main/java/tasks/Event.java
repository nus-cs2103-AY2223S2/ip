package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, formatInput);
        this.to = LocalDateTime.parse(to, formatInput);
    }

    public LocalDate getFromDate() {
        return this.from.toLocalDate();
    }

    public LocalDate getToDate() {
        return this.to.toLocalDate();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from.format(formatOutput) + " " +
                "\n              to: " + to.format(formatOutput) + ")";
    }
}
