package Duke.Commands.Tasks;

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

    private String getTaskClass() {
        return "T";
    }

    private String getFrom() {
        return this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    private String getTo() {
        return this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    public String toString() {
        return String.format("[%s][%s] %s (from: %s to: %s)",
                this.getTaskClass(), this.getStatusIcon(),
                this.description, this.getFrom(), this.getTo());
    }
}
