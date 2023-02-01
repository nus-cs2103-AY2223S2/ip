import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        String[] fromDateTime = from.split(" ");
        String[] toDateTime = to.split(" ");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        if (fromDateTime.length != 2) from += " 0000";    // default to 00:00
        if (toDateTime.length != 2) to += " 2359";        // default to 23:59

        this.from = LocalDateTime.parse(from, formatter);
        this.to = LocalDateTime.parse(to, formatter);
    }

    /**
     * @return the string representation of the task to be stored in the hard disk.
     */
    public String toStorageString() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        return "[E]" + super.toString() + " (from: " + formatter.format(this.from) + " to: " +
                formatter.format(this.to) + ")";
    }
}
