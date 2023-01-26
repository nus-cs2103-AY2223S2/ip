import java.time.LocalDate;

public class TaskDeadline extends Task {
    public final LocalDate endTime;

    public TaskDeadline(String description, String endTime) {
        super(description);
        this.endTime = LocalDate.parse(endTime);
    }

    @Override
    public String toString() {
        return String.format(
            "[D]%s (by: %s)", 
            super.toString(), 
            Task.formatDate(this.endTime)
        );
    }
}
