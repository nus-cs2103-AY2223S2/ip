package DukeBot;

import java.time.DateTimeException;
import java.time.LocalDateTime;

public class Event extends Task{
    private static final String typeToString = "[E]";
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    public Event(String task, String startDateTime, String endDateTime) throws DateTimeException {
        super(task);
        this.type = Types.EVENT;
        this.startDateTime = LocalDateTime.parse(startDateTime);
        this.endDateTime = LocalDateTime.parse(endDateTime);
    }

    @Override
    public String status() {
        String status = this.completed ? "[X]" : "[ ]";
        return typeToString + status + details + " (from:" + this.startDateTime + " to:" + this.endDateTime + ")";
    }
}
