package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;

    public Event(String input, String from, String to) {
        super(input);
        this.fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.fromDateTime.format(
                DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")
        ) + " to: " + this.toDateTime.format(
                DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")
        ) + ")";
    }

    @Override
    public String toTxtString() {
        return "E" + super.toTxtString() + "|" + this.fromDateTime.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        ) + "-" + this.toDateTime.format(
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
        );
    }
}
