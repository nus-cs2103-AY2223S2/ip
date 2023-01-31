package duke;

import java.time.LocalDateTime;

public class Event extends Task {

    private final LocalDateTime fromTime;
    private final LocalDateTime toTime;

    public Event(String taskDescription, LocalDateTime fromTime, LocalDateTime toTime) {
        setTaskDescription(taskDescription);
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s from: %s to: %s", super.toString(),
                fromTime.format(getFormatter()), toTime.format(getFormatter()));
    }
}
