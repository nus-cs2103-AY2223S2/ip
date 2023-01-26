package duke.task;

import java.time.LocalDateTime;

public class Event extends Task {
    protected String from;
    protected String to;
    protected LocalDateTime fromDateTime;
    protected LocalDateTime toDateTime;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
        this.fromDateTime = Task.convertDateTime(from);
        this.toDateTime = Task.convertDateTime(to);
    }

    public String getFrom() {

        return from;
    }

    public String getTo() {

        return to;
    }

    @Override
    public String toString() {
        return "[E]" + "[" +this.getStatusIcon() + "] " + this.getDescription() + " (from: " + dateTimeToString(fromDateTime) + " to: " + dateTimeToString(toDateTime)  + ")";
    }
}