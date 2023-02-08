package duke.task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String input, String deadline) {
        super(input);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(
                DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")";
    }

    @Override
    public String toTxtString() {
        return "D" + super.toTxtString() + "|" + this.deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
}
