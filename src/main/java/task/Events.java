package task;
import java.time.LocalDateTime;

public class Events extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Events(String taskName, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskName);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + startTime + " : " + endTime + ")";
    }
}
