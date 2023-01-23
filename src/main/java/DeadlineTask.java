import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private static final String EVENT_SYMBOL = "D";
    private LocalDateTime endTime;

    public DeadlineTask(String description, LocalDateTime endTime) {
        super(description, EVENT_SYMBOL);
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return super.toString() + " (by: " + endTime.format(formatter) + ")";
    }

    @Override
    public String toSaveString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return super.toSaveString() + "|" + endTime.format(formatter);
    }
}