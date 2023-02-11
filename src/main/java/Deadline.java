import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime deadline;

    public Deadline(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String taskToData() {
        return String.format("[D] | %s | %s | %s", this.getStatusIcon(), this.name, this.deadline);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", name, deadline.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }
}
