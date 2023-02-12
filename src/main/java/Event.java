import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String str, String from, String to) {
        super(str);
        this.from = LocalDateTime.parse(from, INPUT_FORMAT);
        this.to = LocalDateTime.parse(to, INPUT_FORMAT);
        ;
    }

    @Override
    public String getType() {
        return "E";
    }

    public String getFrom() {
        return this.from;
    }

    public String getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.from.format(OUTPUT_FORMAT) + " to: "
                + this.to.format(OUTPUT_FORMAT) + ")";
    }
}
