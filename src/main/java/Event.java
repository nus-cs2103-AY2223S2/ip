import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Event extends Task{

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String value, LocalDateTime from, LocalDateTime to ) {
        super(value);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }
}
