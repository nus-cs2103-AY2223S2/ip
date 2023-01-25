package duke;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public Event(boolean isDone, String description, LocalDateTime from, LocalDateTime to) {
        super(isDone, description);
        this.from = from;
        this.to = to;
    }

    LocalDateTime getFrom() {
        return from;
    }

    void setFrom(LocalDateTime from) {
        this.from = from;
    }

    LocalDateTime getTo() {
        return to;
    }

    void setTo(LocalDateTime to) {
        this.to = to;
    }

    @Override
    public String toCsv() {
        return "E," + super.toCsv() + ","
                + from + "," + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (From: "
                + from.getDayOfMonth() + " " + from.getMonth() + " " + from.getYear() + " "
                + from.getHour() + from.getMinute() // bugalert: may not always be 4-digits
                + " To: "
                + to.getDayOfMonth() + " " + to.getMonth() + " " + to.getYear() + " "
                + to.getHour() + to.getMinute() // bugalert: may not always be 4-digits
                + ")";
    }
}
