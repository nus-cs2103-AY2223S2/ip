import java.time.LocalDate;
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from.substring(1, from.length()-1));
        this.to = LocalDate.parse(to.substring(1, to.length()));
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + "(from: " + from + " to: " + to + ")");
    }
}