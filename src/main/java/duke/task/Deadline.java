package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate by;
    protected String taskType;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.taskType = "D";
    }

    public String toString() {
        String formattedByDate = this.by.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        return "[" + this.taskType + "][" + super.status + "] "
                + super.description
                + " (BY: " + formattedByDate
                + ")";
    }
}
