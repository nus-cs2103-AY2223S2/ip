package dukes.engine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class Event extends Task {
    LocalDate start;
    LocalDate end;

    Event(String taskName, LocalDate start, LocalDate end) {
        super(taskName);
        this.tag = "E";
        this.start = start;
        this.end = end;
    }

    @Override
    public LocalDate getStart() {
        return this.start;
    }

    @Override
    public LocalDate getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        String startFormat = this.start.format(
                DateTimeFormatter.ofPattern("MMM d yyyy", new Locale("en"))
        );
        String endFormat = this.end.format(
                DateTimeFormatter.ofPattern("MMM d yyyy", new Locale("en"))
        );
        return "[E]" + super.toString() + " (from: " +
                startFormat + " to: " + endFormat + ")";
    }
}
