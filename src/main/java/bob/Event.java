package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a type of Task that also tracks the start date and end date
 */
public class Event extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Event(String description, LocalDate start, LocalDate end) {
        super(description, "E");
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return String.format("%s | %s to %s", this.description, this.start.format(f), this.end.format(f));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Event) {
            Event e = (Event) o;
            return this.description.equals(e.description)
                    && this.start.isEqual(e.start)
                    && this.end.isEqual(e.end);
        }
        return false;
    }
}
