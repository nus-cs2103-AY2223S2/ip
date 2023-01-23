import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{
    private final LocalDateTime deadline;
    private final String stringDeadline;

    public Deadlines(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
        this.stringDeadline = deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
    }

    public Deadlines(String description, String deadline) {
        super(description);
        this.deadline = null;
        this.stringDeadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                stringDeadline);
    }
}
