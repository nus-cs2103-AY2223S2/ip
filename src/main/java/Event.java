import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    protected String from,to;
    protected LocalDateTime fromDateTime,toDateTime;

    public Event(String TaskName, String from, String to) {
        super(TaskName);
        this.from = from;
        this.to = to;
        try {
            this.fromDateTime = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
            this.toDateTime = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
        }
        catch (DateTimeParseException err) {
            this.fromDateTime = null;
            this.toDateTime = null;
        }
    }

    public String getFromTime() {
        if (this.fromDateTime != null) {
            return fromDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm")) + " hrs";
        }
        else {
            return this.from;
        }
    }

    public String getToTime() {
        if (this.toDateTime != null) {
            return toDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HHmm")) + " hrs";
        }
        else {
            return this.to;
        }
    }

    @Override
    public String toString() {
        return "[E]" + "[" + super.getStatus() + "]" + super.toString()
                + " (from: " + this.getFromTime() + " to: " + this.getToTime() + ")";
    }

    @Override
    public String toSaveString() {
        return "E" + "=" + super.getStatus() + "=" + super.toSaveString().strip()
                + "=" + this.getFromTime() + "=" + this.getToTime();
    }
}