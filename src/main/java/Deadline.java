import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate deadline;
    Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
