import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
public class Deadlines extends Task {
    private static final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected LocalDateTime deadline;
    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = LocalDateTime.parse(deadline.substring(3), formatter);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)",
                this.deadline.format(formatter));
    }
}
