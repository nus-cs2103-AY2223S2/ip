package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDate endDate;
    public Deadline(String task, String endDate) throws DateTimeParseException {
        super(task, false);
        this.endDate = LocalDate.parse(endDate);
    }

    public Deadline(String task, boolean isCompleted, String endDate) {
        super(task, isCompleted);
        this.endDate = LocalDate.parse(endDate);
    }

    @Override
    public String getTaskType() {
        return "Deadline";
    }

    @Override
    public String getStatus() {
        return String.format("%s", isCompleted());
    }

    @Override
    public String getDescription() {
        return getTask() + " | " + endDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
