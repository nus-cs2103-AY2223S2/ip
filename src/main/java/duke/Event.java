package duke;

import java.time.LocalDateTime;

public class Event extends Task {

    private final LocalDateTime fromTime;
    private final LocalDateTime toTime;

    public Event(String taskDescription, LocalDateTime fromTime, LocalDateTime toTime) {
        this.taskDescription = taskDescription;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s from: %s to: %s", super.toString(),
                fromTime.format(formatter), toTime.format(formatter));
    }
}
