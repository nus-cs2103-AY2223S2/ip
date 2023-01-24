package tasks;
import java.time.*;
import java.time.format.*;

/**
 * Events are tasks which start at a given date/time and end at a given date/time
 */
public class Event extends Task {
    private LocalDateTime eventStart;
    private LocalDateTime eventEnd;

    public Event(String description, LocalDateTime eventStart, LocalDateTime eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    @Override
    public String getDataString() {
        return "E | " + super.getDataString() + " | " + start + "-" + end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " +
                eventStart.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) +
                " to: " + eventEnd.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
    }
}