import java.time.LocalDate;

public class TaskEvent extends Task {
    public final LocalDate fromTime;
    public final LocalDate toTime;

    public TaskEvent(String description, String fromTime, String toTime) {
        super(description);
        this.fromTime = LocalDate.parse(fromTime);
        this.toTime = LocalDate.parse(toTime);
    }

    @Override
    public String toString() {
        return String.format(
            "[E]%s (from: %s to: %s)", 
            super.toString(), 
            Task.formatDate(this.fromTime), 
            Task.formatDate(this.toTime)
        );
    }
}
