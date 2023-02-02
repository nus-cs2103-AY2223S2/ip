package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected String from;
    protected String to;
    protected LocalDateTime fromDate;
    protected LocalDateTime toDate;
    protected String displayFrom;
    protected String displayTo;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        fromDate = LocalDateTime.parse(from, format);
        toDate = LocalDateTime.parse(to, format);
        displayFrom = this.fromDate.toString().replace("T", " ");
        displayTo = this.toDate.toString().replace("T", " ");
    }

    public Event(boolean isDone, String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.isDone = isDone;
        fromDate = LocalDateTime.parse(from, format);
        toDate = LocalDateTime.parse(to, format);
        displayFrom = this.fromDate.toString().replace("T", " ");
        displayTo = this.toDate.toString().replace("T", " ");
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + this.displayFrom + " to: " + this.displayTo + ")";
    }

    @Override
    public String getCommand() {
        return this.isDone
            ? "1 event " + this.description + " /from " + this.from + " /to " + this.to
            : "0 event " + this.description + " /from " + this.from + " /to " + this.to;

    }
}
