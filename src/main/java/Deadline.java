import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    protected LocalDateTime by;
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        return "[D]" + super.getStatusIcon() + " (by: " + dateFormat.format(this.by) + ")";
    }

    @Override
    public String toRecord() {
        return "D|" + super.toRecord() + "|" + by;
    }
}
