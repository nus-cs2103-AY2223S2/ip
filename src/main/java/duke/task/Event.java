package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d LLL uuuu, hh:mm a");

    public Event(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(description);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + startDateTime.format(formatter)
                + " to: " + endDateTime.format(formatter) + ")";
    }

    @Override
    public String getTaskState() {
        return "E | " + super.getTaskString()
                + " | " + startDateTime + " | " + endDateTime;
    }
}
