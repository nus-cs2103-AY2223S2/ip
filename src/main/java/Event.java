import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

    protected final LocalDate from;
    protected final LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        String fromStr = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String toStr = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (from: " + fromStr + " to: " + toStr + ")";
    }
}