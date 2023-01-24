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
    public String toSaveString(Storage storage) {
        return super.toSaveString(storage) + "|" + storage.formatDateTime(startTime)
                + "|" + storage.formatDateTime(endTime);
    }
}