package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    private final LocalDateTime eventStart;
    private final LocalDateTime eventEnd;
    private static final DateTimeFormatter EVENT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Event(String description, LocalDateTime eventStart, LocalDateTime eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    public static DateTimeFormatter getEventFormatter() {
        return EVENT_FORMAT;
    }

    @Override
    public String getDataString() {
        return "E | " + super.getDataString() + " | " + this.eventStart.format(getEventFormatter()) + " to " +
                this.eventEnd.format(getEventFormatter());
    }

    @Override
    public String toString() {
        String timeFrame;
        if ((eventStart.getYear() == eventEnd.getYear()) && (eventStart.getDayOfYear() == eventEnd.getDayOfYear())) {
            timeFrame = eventStart.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) + " " +
                    eventStart.format(DateTimeFormatter.ofPattern("HH:mm")) + " to: " +
                    eventEnd.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
        } else {
            timeFrame = eventStart.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) + " " +
                    eventStart.format(DateTimeFormatter.ofPattern("HH:mm")) + " to: " +
                    eventEnd.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)) + " " +
                    eventEnd.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
        }
        return "[E]" + super.toString() + " (from: " + timeFrame;
    }
}