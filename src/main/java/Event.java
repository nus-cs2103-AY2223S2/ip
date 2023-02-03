import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        this(description, from, to, false);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + timeToString(from) + " to: " + timeToString(to) + ")";
    }

    public static String timeToString(LocalDateTime dateTime) {
        String str = dateTime.toString();
        if (str.substring(11).equals("12:34:56")) {
            return str.substring(0, 10);
        }
        return str.substring(0, 10) + " " + str.substring(11, 16);
    }

    @Override
    public String saveAsStr() {
        return "E" + super.saveAsStr() + "~%~" + from + "~%~" + to;
    }
}
