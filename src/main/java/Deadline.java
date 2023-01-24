import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected static final String DATETIME_DISPLAY_PATTERN = "MMM d yyyy hh:mm";
    protected LocalDateTime endDate;

    Deadline(String description, LocalDateTime endDate) {
        super(description);
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return String.format("Deadline/" + getTaskDescription() + "(by " + endDate.format(DateTimeFormatter.ofPattern(DATETIME_DISPLAY_PATTERN)) + ")");
    }
}
