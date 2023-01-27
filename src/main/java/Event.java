import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends Task {
    LocalDateTime start;
    LocalDateTime end;

    Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    Event(String description, boolean isDone, LocalDateTime start, LocalDateTime end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    String getSaveTaskString() {
        return String.format("E | %s | from: %s | to: %s", super.toString(), formatSavedDateTime(start),
                formatSavedDateTime(end));
    }

    @Override
    public String toString() {
        return String.format("E | %s | from: %s to: %s", super.toString(), formatDateTime(start), formatDateTime(end));
    }
}
