package dukes.engine;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

class DeadLine extends Task {
    LocalDate deadline;

    DeadLine(String taskName, LocalDate deadline) {
        super(taskName);
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
