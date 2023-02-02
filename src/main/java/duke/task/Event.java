package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime fromTime;
    protected LocalDateTime toTime;
    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("EEE, d MMM yyyy hh:mma");

    public Event(String taskName, LocalDateTime fromTime, LocalDateTime toTime) {
        super(taskName);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public Event(String taskName, LocalDateTime fromTime, LocalDateTime toTime, boolean isDone) {
        super(taskName);
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.isDone = isDone;
    }

    @Override
    public String[] encode() {
        String[] res = new String[]{"E", this.getStatusIcon(), this.taskName, this.fromTime.toString(),
                this.toTime.toString()};
        return res;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + fromTime.format(DATETIME_FORMAT) + " to: " +
                toTime.format(DATETIME_FORMAT) +")";
    }
}