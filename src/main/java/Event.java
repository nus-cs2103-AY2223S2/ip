import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime dueDateTime;

    public Event(String description, String from, String to) throws InvalidDateTimeException {
        super(description);

        this.startDateTime = handleDateTime(from);
        this.dueDateTime = handleDateTime(to);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s %s to: %s %s)", super.toString(),
                this.startDateTime.toLocalDate(), this.startDateTime.toLocalTime(),
                this.dueDateTime.toLocalDate(), this.startDateTime.toLocalTime());
    }
}
