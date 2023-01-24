package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, LocalDate start, LocalDate end, boolean isDone) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + " (from: " + this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: " + this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    public String toFile() {
        return "E | " + (this.isDone ? "1" : "0") + " | " + this.description + " | " + this.start + " | " + this.end;
    }
}
