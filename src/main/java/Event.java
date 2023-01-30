import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) throws DukeException{
        super(description);
        this.from = from;
        this.to = to;

    }

    public LocalDate getFrom() {
        return this.from;
    }

    public LocalDate getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        String fromDateFormat = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        String toDateFormat = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return "[E]" + super.toString() + "(from: " + fromDateFormat + "to: " + toDateFormat + ")";
    }

    @Override
    public String toFileString() {
        String fromDateFormat = this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        String toDateFormat = this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
        return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description, fromDateFormat, toDateFormat);
    }
}