import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task{
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final String stringFrom;
    private final String stringTo;

    public Events(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.stringFrom = from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        this.to = to;
        this.stringTo = to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    public Events(String description, String from, String to) {
        super(description);
        this.from = null;
        this.stringFrom = from;
        this.to = null;
        this.stringTo = to;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), stringFrom, stringTo);
    }
}
