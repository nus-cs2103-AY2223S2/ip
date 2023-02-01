package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    String startString = null;
    String endString = null;
    LocalDate startDate;
    LocalDate endDate;
    public Event(String details, String start, String end) {
        super(details);
        try {
            this.startDate = LocalDate.parse(start);
            this.startString = startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
            this.endDate = LocalDate.parse(end);
            this.endString = endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException ignored) {
            if (startString == null) {
                this.startString = start;
                this.startDate = null;
            }
            if (endString == null) {
                this.endString = end;
                this.endDate = null;
            }
        }
    }

    @Override
    public String toString() {
        String task = super.toString();
        return "[E] " + task + " (from: " + startString + " to: " + endString + ")";
    }

    @Override
    public String toStorageString() {
        return "E#" + super.toStorageString() + "#" + startString + "#" + endString;
    }
}
