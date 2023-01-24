import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
public class Deadlines extends Task {

    protected LocalDateTime deadline;
    public Deadlines(String description, String deadline) {
        this(false, description, deadline);
    }

    public Deadlines(boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)",
                this.deadline.format(printFormatter));

    }
    public String formatText() {
        String divider = " | ";
        String isMarked = this.isDone ? "1" : "0";
        return "D" + divider + isMarked + divider + this.description + divider + this.deadline;
    }

}
