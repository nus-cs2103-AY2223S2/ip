import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Quest {
    private LocalDateTime from;
    private LocalDateTime to;
    private static DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("HHmm dd/MM/yy");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mma");

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, inputFormatter);
        this.to = LocalDateTime.parse(to, inputFormatter);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + " from: " +
                from.format(outputFormatter) + " to: " + to.format(outputFormatter);
    }
}
