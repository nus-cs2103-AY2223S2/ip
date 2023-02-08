import java.time.LocalDateTime;

public class Event extends Task {
    LocalDateTime from;
    LocalDateTime to;

    Event(String desc, boolean done, LocalDateTime from, LocalDateTime to) {
        super(desc, done);
        this.from = from;
        this.to = to;
    }

    String getFrom(){
        return this.from.toString();
    }

    String getTo(){
        return this.to.toString();
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + formatDate(this.from) + " to " + formatDate(this.to) + ")";
    }
}
