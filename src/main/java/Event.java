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
            this.startString = startDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.endDate = LocalDate.parse(end);
            this.endString = endDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } catch (DateTimeParseException ignored) {
            if (startString == null) {
                this.startString = start;
            }
            if (endString == null) {
                this.endString = end;
            }
        }
    }

    @Override
    public String toString() {
        String task = super.toString();
        return "[E] " + task + " (from: " + startString + " to: " + endString + ")";
    }
}
