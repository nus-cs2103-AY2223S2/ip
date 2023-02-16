package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Event is a type of task that has a "from" and "to" date.
 */
public class Event extends Task {

    private LocalDateTime fromDate;
    private LocalDateTime toDate;
    private final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy H:mm");

    public Event(String description, String from, String to) {
        super(description);
        fromDate = LocalDateTime.parse(from, FORMATTER);
        toDate = LocalDateTime.parse(to, FORMATTER);
    }

    @Override
    public String getIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return description + " (from: " + formatDate(fromDate) + " to: " + formatDate(toDate) + ")";
    }

    private String formatDate(LocalDateTime date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    public String getFromDate() {
        return fromDate.format(FORMATTER);
    }

    public String getToDate() {
        return toDate.format(FORMATTER);
    }

}
