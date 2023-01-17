import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    String from;
    String to;
    LocalDateTime fromDate;
    LocalDateTime toDate;
    public Event(String title, String from, String to) {
        super(title);
        this.from = from;
        this.to = to;
        this.type = "[E]";
        try {
            this.fromDate = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            this.toDate = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
        } catch (DateTimeParseException e) {
            throw e;
        }
    }
    @Override
    public String toString() {
        return super.toString() + " (from: " + this.fromDate.format(DateTimeFormatter.ofPattern("HHmm MMM d yyyy")) +
                                " to: " + this.toDate.format(DateTimeFormatter.ofPattern("HHmm MMM d yyyy")) + ")";
    }
}
