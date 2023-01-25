package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }
    public Event(String description, boolean isCompleted, LocalDate start, LocalDate end) {
        super(description, isCompleted);
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
    public String toString() {
        String startFormatted = this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endFormatted = this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString()
                + " (from: " + startFormatted + " to: " + endFormatted + ")";
    }
}