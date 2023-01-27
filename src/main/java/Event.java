import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[E]" + super.toString()
                + "(from " + this.from.format(formatter)
                + " to " + this.to.format(formatter) + ")";
    }

    @Override
    public String toSavedString() {
        return "E" + "|" + (super.isDone ? "1" : "0")
                + "|" + super.description + "|" + this.from + "|" + this.to;
    }
}