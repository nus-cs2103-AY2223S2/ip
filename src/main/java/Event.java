import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: "
                + this.formatDateTime(this.startTime)
                + " to: "
                + this.formatDateTime(this.endTime)
                + ")";
    }
}
