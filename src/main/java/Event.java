import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    Event(String name, String from, String to) {
        super(name);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd/HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s %d %d %d:%02d, to: %s %d %d %d:%02d)",
                super.toString(), from.getMonth(), from.getDayOfMonth(), from.getYear(),
                from.getHour(), from.getMinute(), to.getMonth(), to.getDayOfMonth(),
                to.getYear(), to.getHour(), to.getMinute());
    }
}
