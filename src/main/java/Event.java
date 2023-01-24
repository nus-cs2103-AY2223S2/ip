import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String start;
    protected LocalDate startDate;
    protected String end;
    protected LocalDate endDate;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.startDate = LocalDate.parse(start);
        this.endDate = LocalDate.parse(end);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: "
                + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}