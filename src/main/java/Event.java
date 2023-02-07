import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = getStartDateTime(from);
        this.to = getEndDateTime(to);
    }

    public LocalDateTime getStartDateTime(String from) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime startDateTime = LocalDateTime.parse(from, dateTimeFormatter);
        return startDateTime;
    }

    public LocalDateTime getEndDateTime(String from) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        LocalDateTime endDateTime = LocalDateTime.parse(from, dateTimeFormatter);
        return endDateTime;
    }

    public String deadlineDateTime() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
        String startDateTime = this.from.format(format);
        String startDayOfWeek = this.from.getDayOfWeek().toString();
        String endDateTime = this.to.format(format);
        String endDayOfWeek = this.to.getDayOfWeek().toString();
        return " (from: " + startDayOfWeek + ", " + startDateTime
                + " to: " +  endDayOfWeek + ", " + endDateTime + ")";
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {

        return "[E]" + super.toString() + deadlineDateTime();
    }
}
