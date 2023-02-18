package tigerlily.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    private final LocalDateTime eventStart;
    private final LocalDateTime eventEnd;
    private static final DateTimeFormatter EVENT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Constructor for Event
     * @param description the description of the Event
     * @param eventStart the start date and time of the Event
     * @param eventEnd the end date and time of the Event
     */
    public Event(String description, LocalDateTime eventStart, LocalDateTime eventEnd) {
        super(description);
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
    }

    /**
     * Returns the DateTimeFormatter used to format Events.
     *
     * @return the DateTimeFormatter used to format Events
     */
    public static DateTimeFormatter getEventFormatter() {
        return EVENT_FORMAT;
    }

    /**
     * Generates the String representation of the Event needed for data storage.
     *
     * @return the Event in String format for data storage
     */
    @Override
    public String getStorageString() {
        return "E | " + super.getStorageString() + " | " + this.eventStart.format(getEventFormatter()) + " to " +
                this.eventEnd.format(getEventFormatter());
    }

    /**
     * Generates the String representation of the Event, representation depends on whether the Event completes on the
     * same day or not.
     *
     * @return the String representation of the Event
     */
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