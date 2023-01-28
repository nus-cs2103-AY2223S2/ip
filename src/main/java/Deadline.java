import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime byDateTime;
    protected LocalDate byDate;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.byDateTime = by;
    }

    public Deadline(String description, LocalDate by) {
        super(description);
        this.byDate = by;
    }

    public String getFileRepresentation() {
        if (byDateTime != null) {
            return "deadline " + this.isDone + " " + this.description + " /by "
                    + this.byDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else {
            return "deadline " + this.isDone + " " + this.description + " /by "
                    + this.byDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    @Override
    public String toString() {
        if (byDateTime != null) {
            return "[D]" + super.toString() + " (by: " + this.byDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
        } else {
            return "[D]" + super.toString() + " (by: " + this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
}
