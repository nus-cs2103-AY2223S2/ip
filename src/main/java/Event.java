import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = super.split(from);
        this.to = super.split(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }

    @Override
    public String toText() {
        int done = -1;
        if (this.isDone) {
            done = 1;
        } else {
            done = 0;
        }
        return "E" + " | " + done +  " | " + this.description + " | " + this.from + " | " + this.to;
    }
}
