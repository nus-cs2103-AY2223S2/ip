import java.time.LocalDateTime;

public class Event extends Task {
<<<<<<< HEAD
    LocalDateTime from;
    LocalDateTime to;
=======
    private String from;
    private String to;
>>>>>>> branch-Level-8

    Event(String desc, boolean done, LocalDateTime from, LocalDateTime to) {
        super(desc, done);
        this.from = from;
        this.to = to;
    }

    String getFrom(){
        return this.from;
    }

    String getTo(){
        return this.to;
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + formatDate(this.from) + " to " + formatDate(this.to) + ")";
    }
}
