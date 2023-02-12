package wessy.task;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

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
        return "[E]" + super.toString() + " (from: " + toString(from) + " to: " + toString(to) + ")";
    }

    public static String toString(LocalDateTime dateTime) {
        String str = dateTime.toString();
        if (str.substring(11).equals("12:34:56")) {
            return str.substring(0, 10);
        }
        return str.substring(0, 10) + " " + str.substring(11, 16);
    }

    @Override
    public String saveAsStr(String separator) {
        return "E" + super.saveAsStr(separator) + separator + from + separator + to + "\n";
    }
}
