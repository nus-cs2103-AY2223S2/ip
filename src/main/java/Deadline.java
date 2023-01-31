import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description, "D");
        this.deadline = deadline;
    }
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return String.format("%s | by: %s", this.description, this.deadline.format(f));
    }
}
