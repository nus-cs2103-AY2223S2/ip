import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime fromDateTime;
    protected LocalDate fromDate;
    protected LocalDateTime toDateTime;
    protected LocalDate toDate;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.fromDateTime = from;
        this.toDateTime = to;
    }

    public Event(String description, LocalDate from, LocalDateTime to) {
        super(description);
        this.fromDate = from;
        this.toDateTime = to;
    }

    public Event(String description, LocalDateTime from, LocalDate to) {
        super(description);
        this.fromDateTime = from;
        this.toDate = to;
    }

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.fromDate = from;
        this.toDate = to;
    }

    public String getFileRepresentation() {
        if (fromDateTime != null && toDateTime != null) {
            return "event " + this.isDone + " " + this.description
                    + " /from " + this.fromDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    + " /to " + this.toDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else if (fromDateTime != null) {
            return "event " + this.isDone + " " + this.description
                    + " /from " + this.fromDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                    + " /to " + this.toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else if (toDateTime != null) {
            return "event " + this.isDone + " " + this.description
                    + " /from " + this.fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    + " /to " + this.toDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } else {
            return "event " + this.isDone + " " + this.description
                    + " /from " + this.fromDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    + " /to " + this.toDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }
    }

    @Override
    public String toString() {
        if (fromDateTime != null && toDateTime != null) {
            return "[E]" + super.toString()
                    + " (from: " + this.fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                    + " to: " + this.toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
        } else if (fromDateTime != null) {
            return "[E]" + super.toString()
                    + " (from: " + this.fromDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                    + " to: " + this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        } else if (toDateTime != null) {
            return "[E]" + super.toString()
                    + " (from: " + this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " to: " + this.toDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
        } else {
            return "[E]" + super.toString()
                    + " (from: " + this.fromDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + " to: " + this.toDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
        }
    }
}
