package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = DateTimeParser.parseInput(startTime);
        this.endTime = DateTimeParser.parseInput(endTime);
    }

    @Override
    public String printTask() {
        return String.format("[E][%s] %s (%s - %s)",
                (super.isDone() ? "X" : " "),
                super.getDescription(),
                DateTimeParser.formatOutput(this.startTime),
                DateTimeParser.formatOutput(this.endTime));
    }
}
