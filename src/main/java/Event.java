import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Event extends Task {
    private LocalDate from;
    private LocalDate to;

    public Event(String name, String from, String to) throws DateTimeParseException {
        super(name);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: "
            + this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " to: "
            + this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
