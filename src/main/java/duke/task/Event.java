package duke.task;

import java.time.LocalDateTime;

/**
 * An Event object is a Task with a start time and end time.
 */
public class Event extends Task {
    LocalDateTime startTime;
    LocalDateTime endTime;
    public Event(String title, String startTime, String endTime) {
        super(title);
        this.startTime = super.parseDateString(startTime);
        this.endTime = super.parseDateString(endTime);
    }

    public Event(String title, String startTime, String endTime, boolean isDone) {
        super(title, isDone);
        this.startTime = super.parseDateString(startTime);
        this.endTime = super.parseDateString(endTime);
    }

    @Override
    public String toDiskFormat() {
        return String.format("D|%d|%s|%s|%s", super.getIsDone() ? 1 : 0, super.getTitle(),
                this.startTime, this.endTime);
    }

    @Override
    public String toString() {
        return String.format("[%s][E] %s (from: %s to %s)", super.getIsDone() ? "X" : " ",
                super.getTitle(), super.dateTimeToString(this.startTime),
                super.dateTimeToString(this.endTime));
    }
}
