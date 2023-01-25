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

    @Override
    public String toString() {
        String startFormatted = this.start.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String endFormatted = this.end.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString()
                + " (from: " + startFormatted + " to: " + endFormatted + ")";
    }
}