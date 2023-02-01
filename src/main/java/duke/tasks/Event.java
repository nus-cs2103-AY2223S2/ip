package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    static DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected LocalDate to;


    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(format) + " to: " + to.format(format) + ")";
    }

    @Override
    String getTypeIcon() {
        return "[E]";
    }
}
