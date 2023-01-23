import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String taskDescription, LocalDateTime deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        String deadlineString = " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("EEE MMM d yyyy hh:mma")) + ")";
        return "[D]" + super.toString() + deadlineString;
    }
}
