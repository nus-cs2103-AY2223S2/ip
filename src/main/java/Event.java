import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        try {
            this.from = LocalDate.parse(from);
            this.to = LocalDate.parse(to);
        } catch (DateTimeParseException e) {
            throw DukeException.DATETIME_FORMAT;
        } catch (Exception e) {
            throw new DukeException("Unknown error occurred when parsing datetime.");
        }
    }

    @Override
    public String toString() {
        String formattedFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        String formattedTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return String.format("[E]%s (from: %s, to: %s)", super.toString(), formattedFrom, formattedTo);
    }
}
