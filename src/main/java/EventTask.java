import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private static final String EVENT_SYMBOL = "E";
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EventTask(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description, EVENT_SYMBOL);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return super.toString() + " (from: " + startTime.format(formatter) + " to: " + endTime.format(formatter) + ")";
    }

    @Override
    public String toSaveString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return super.toSaveString() + "|" + startTime.format(formatter) + "|" + endTime.format(formatter);
    }
}