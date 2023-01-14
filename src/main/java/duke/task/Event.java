package duke.task;

import java.time.LocalDateTime;

import duke.utils.DateUtil;

/**
 * Event
 */
public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String title, LocalDateTime from, LocalDateTime to) {
        this(title, false, from, to);
    }

    public Event(String title, boolean isDone, LocalDateTime from, LocalDateTime to) {
        super(title, isDone);
        this.from = from;
        this.to = to;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public String toCsv() {
        return "E," + super.toCsv() + "," + DateUtil.dateToString(from) + "," + DateUtil.dateToString(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " 
            + DateUtil.dateToString(from)
            + " to: " 
            + DateUtil.dateToString(to)
            + ")";
    }
}
