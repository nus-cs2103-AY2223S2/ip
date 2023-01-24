import java.util.concurrent.atomic.AtomicLong;

public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String value, String from, String to ) {
        super(value);
        this.from = from;
        this.to = to;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + from + " to: " + to + ")";
    }
}
