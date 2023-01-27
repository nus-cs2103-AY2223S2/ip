import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    public Event(String taskName, String from, String to) throws DukeException {
        super(taskName);
        try {
            LocalDate fromDate = LocalDate.parse(from);
            LocalDate toDate = LocalDate.parse(to);
            this.from = fromDate;
            this.to = toDate;
        } catch (DateTimeParseException e) {
            throw new DukeException("Format of date was not recognized");
        }
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " to: " + this.to.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + ")";
    }
}
