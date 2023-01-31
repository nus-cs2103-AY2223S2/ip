package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.getStatusIcon() + " "
                + super.getDescription() + " (from: " + this.from.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"))
                + " to: " + this.to.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm")) + ")";
    }

    public static Event addEvent(String details) {
        String description = details.substring(0, details.indexOf(" /from"));
        String fromAndTo = details.substring(details.indexOf(" /from") + " /from".length() + 1);
        String fromString = fromAndTo.substring(0, fromAndTo.indexOf(" /to"));
        String toString = fromAndTo.substring(fromAndTo.indexOf(" /to") + " /to".length() + 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime from = LocalDateTime.parse(fromString, formatter);
        LocalDateTime to = LocalDateTime.parse(toString, formatter);

        return new Event(description, from, to);
    }
}