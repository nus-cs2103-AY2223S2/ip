import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private String taskName;
    private LocalDateTime deadline;
    private String dataFormat;

    public Deadline(String taskName, LocalDateTime deadline, String dataFormat) {
        super(taskName);
        this.taskName = taskName;
        this.deadline = deadline;
        this.dataFormat = dataFormat;
    }

    public Deadline(String taskName, Boolean isDone, String dataFormat) {
        super(taskName, isDone);
        this.taskName = taskName;
        this.deadline = LocalDateTime.parse(dataFormat);
        this.dataFormat = dataFormat;
    }

    @Override
    public String dataFormat() {
        return "D|" + super.dataFormat() + "|" + this.dataFormat;
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
