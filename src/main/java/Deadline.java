import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime dueDateTime;

    public Deadline(String description, String by) throws InvalidDateTimeException {
        super(description);
        this.dueDateTime = handleDateTime(by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s %s)", super.toString(),
                dueDateTime.toLocalDate(), dueDateTime.toLocalTime());
    }
}
