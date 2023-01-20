import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime dateTime;
    public Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + dateTime.format(Task.format) + ")";
    }
}
