import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

public class Event extends Task{

    protected LocalDateTime from;
    protected LocalDateTime to;


    public Event(String value, LocalDateTime from, LocalDateTime to, boolean mark ) {
        super(value,mark);
        this.from = from;
        this.to = to;
    }

    public String toFile() {
        return "event," + super.isMark() + "," + super.getValue() + "," + this.from + "," + this.to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }
}
