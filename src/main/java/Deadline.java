import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    protected String by;
    protected LocalDateTime byDateTime;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.byDateTime =  Task.convertDateTime(by);
    }

    public String getBy() {
        return by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + "[" +this.getStatusIcon() + "] " + this.getDescription() + " (by: " + dateTimeToString(byDateTime) + ")";
    }
}