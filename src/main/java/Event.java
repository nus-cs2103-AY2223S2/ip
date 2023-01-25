import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        String fromStr = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String toStr = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        return "[E]" + super.toString() + "(from: " + fromStr + " to: " + toStr + ")";
    }
}