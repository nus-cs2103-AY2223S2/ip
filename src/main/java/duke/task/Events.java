package duke.task;

import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected LocalDateTime startTime;

    protected LocalDateTime endTime;

    public Events(String description, String startTime, String endTime) {
        super(description);
        DateTimeFormatter formatterStart = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm ");
        this.startTime = LocalDateTime.parse(startTime, formatterStart);
        DateTimeFormatter formatterEnd = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.endTime = LocalDateTime.parse(endTime, formatterEnd);
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) +
                " to: " + endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }
}