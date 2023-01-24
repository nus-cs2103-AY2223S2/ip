package dukes.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DeadLine extends Task {
    LocalDate deadline;

    public DeadLine(String taskName, LocalDate deadline) {
        super(taskName);
        this.tag = "D";
        this.deadline = deadline;
    }

    public DeadLine(String taskName, boolean isDone, LocalDate deadline) {
        super(taskName, isDone);
        this.tag = "D";
        this.deadline = deadline;
    }

    @Override
    public LocalDate getDeadLine() {
        return this.deadline;
    }

    @Override
    public String toString() {
        String dateFormat = this.deadline.format(
                DateTimeFormatter.ofPattern("MMM d yyyy", new Locale("en"))
        );
        return "[D]" + super.toString() + " (by: " +
                dateFormat + ")";
    }
}
