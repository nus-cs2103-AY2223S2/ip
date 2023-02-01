import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Tasks{
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.to = to;
        this.from = from;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.from.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) 
        + " to: " + this.to.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)) +")";
    }

    @Override
    public String log() {
        return "E" + super.log() + " | " + this.from + " - " + this.to +"\n";
    }
}
