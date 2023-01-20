import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    Event(String title, String from, String to) throws DukeException {
        super(title);
        this.from = LocalDateTime.parse(from.replace("/from", "").trim());
        this.to = LocalDateTime.parse(to.replace("/to", "").trim());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}