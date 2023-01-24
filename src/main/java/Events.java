import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Events extends Task {
    protected LocalDateTime start;
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected LocalDateTime end;
    public Events(String description, String start, String end) {
        super(description);

        this.start = LocalDateTime.parse(start.substring(5), formatter);
        this.end = LocalDateTime.parse(end.substring(3), formatter);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)", this.start.format(formatter),
                this.end.format(formatter));
    }
}
