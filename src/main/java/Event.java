import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String taskDescription, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskDescription);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        String eventString = " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy hh:mma"))
                + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy hh:mma")) + ")";
        return "[E]" + super.toString() + eventString;
    }
}
