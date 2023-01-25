import java.time.LocalDateTime;

/**
 * Represents an Event object. An Even object has a start time and end time.
 */
public class Event extends Task {
    LocalDateTime startTime;
    LocalDateTime endTime;
    public Event(String title, String startTime, String endTime) {
        super(title);
        this.startTime = super.parseDateString(startTime);
        this.endTime = super.parseDateString(endTime);
    }

    @Override
    public String toString() {
        return String.format("[%s][E] %s (from: %s to %s)", super.getIsDone() ? "X" : " ",
                super.getTitle(), super.dateTimeToString(this.startTime),
                super.dateTimeToString(this.endTime));
    }
}
