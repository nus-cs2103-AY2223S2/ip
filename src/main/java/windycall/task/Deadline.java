package windycall.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    private LocalDate deadline;


    public Deadline(String description, boolean status, LocalDate deadline) {
        super(description, status);
        this.deadline = deadline;
    }

    public Deadline(String description, boolean status, LocalDate deadline, String tag) {
        super(description, status, tag);
        this.deadline = deadline;
    }

    @Override
    public String getTaskTypeIcon() {
        return "D";
    }


    @Override
    public String getFileFormat() {
        return "D | " + getStatusIcon() + " | " + tag + " | " + description + " | " + deadline + "\n";
    }

    private String changeDateTimeFormat() {
        return deadline.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    private String getDeadline() {
        return " (by: " + changeDateTimeFormat() + ")";
    }

    @Override
    public String toString() {
        return "[" + getTaskTypeIcon() + "]" + getCurrentDescription() + this.getDeadline();
    }
}
