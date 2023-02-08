package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String input, String from, String to) {
        super(input);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(
                DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")
        ) + " to: " + this.to.format(
                DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")
        ) + ")";
    }

    @Override
    public String toTxtString() {
        return "E" + super.toTxtString() + "|" + this.from.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        ) + "-" + this.to.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        );
    }
}
