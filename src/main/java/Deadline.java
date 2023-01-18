import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private String taskName;
    private LocalDateTime deadline;

    public Deadline(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.taskName = taskName;
        this.deadline = deadline;
    }

    public Deadline(String taskName, Boolean isDone, String deadline) {
        super(taskName, isDone);
        this.taskName = taskName;
        this.deadline = deadline;
    }

    @Override
    public String dataFormat() {
        return "D|" + super.dataFormat() + "|" + this.deadline;
    }


    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + this.formatDateTime(this.deadline)
                + ")";
    }
}
