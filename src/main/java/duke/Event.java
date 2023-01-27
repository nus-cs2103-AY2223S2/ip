package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toLog() {
        return "E" + super.toLog() + " | "
                + this.startTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + " - "
                + this.endTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                + "\n";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: "
                + this.startTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))
                + " to: "
                + this.endTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT))
                + ")";
    }
}
