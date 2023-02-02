package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime byTime;
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mma");

    public Deadline(String taskName, LocalDateTime byTime) {
        super(taskName);
        this.byTime = byTime;
    }

    public Deadline(String taskName, LocalDateTime byTime, boolean isDone) {
        super(taskName);
        this.byTime = byTime;
        this.isDone = isDone;
    }

    @Override
    public String[] encode() {
        String[] res = new String[]{"D", this.getStatusIcon(), this.taskName, this.byTime.toString()};
        return res;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + byTime.format(DATETIME_FORMAT) + ")";
    }
}