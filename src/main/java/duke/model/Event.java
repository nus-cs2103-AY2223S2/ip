package duke.model;
import java.time.LocalDateTime;

import duke.command.utils.DateTimeStringParser;
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;


    protected Event(String taskDescription, LocalDateTime startTime, LocalDateTime endTime) {
        super(taskDescription);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + startTime.format(DateTimeStringParser.DISPLAY_FORMAT) + " to: "
                + endTime.format(DateTimeStringParser.DISPLAY_FORMAT) + ")";
    }

    @Override
    boolean isDueOn(LocalDateTime time) {
        return time.isBefore(endTime) && time.isAfter(startTime);
    }
}
