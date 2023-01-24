package connor.task;

import connor.parser.Parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Deadline extends Task {
    private String taskName;
    private LocalDateTime deadline;
    private String dataFormat;

    public Deadline(String taskName, String task) {
        super(taskName);
        this.taskName = taskName;
        try {
            this.deadline = parseDateTime(task);
            this.dataFormat = dateTimeFormat(task);
        } catch (DateTimeException e) {
            System.out.println("        " + e.getMessage());
        }
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
