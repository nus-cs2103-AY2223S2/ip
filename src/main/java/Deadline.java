import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private String doBy;
    private LocalDateTime deadline;
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("EEEE, MMMM d yyyy, h:mm a");

    public Deadline (String description, String deadline) {
        super(description);
        this.doBy = deadline;
        this.deadline = LocalDateTime.parse(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.getDescription() +
                " (by: " + this.deadline.format(DATE_TIME_FORMATTER) + ")";
    }
}
