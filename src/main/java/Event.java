import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected String by;
    private LocalDate from;
    private LocalDate to;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDate.parse(from.trim(), formatter);
            this.to = LocalDate.parse(to.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter a valid date in e.g yyyy-mm-dd format!");
        }
    }

    @Override
    public String toString() {
        return "\t[E]" + super.toString() + " (from: " + from + "to: " + to + ")";
    }
}
