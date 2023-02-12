package duke.task;

import java.time.LocalDateTime;

/**
 * This class creates an Event type object which inherits from Task
 * Event(String description , boolean isDone, LocalDateTime from date, LocalDateTime to date)
 *
 * @author He Shuimei
 * @version 0
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String desc, boolean done, LocalDateTime from, LocalDateTime to) {
        super(desc, done);
        this.from = from;
        this.to = to;
    }

    /**
     * getter for "from" date
     * @return this.from
     */
    public String getFrom(){
        return format24HrDate(this.from);
    }

    /**
     * getter for "to" date
     * @return this.to
     */
    public String getTo(){
        return format24HrDate(this.to);
    }

    @Override
    public String toString(){
        return "[E]" + super.toString() + " (from: " + formatDate(this.from) + " to " + formatDate(this.to) + ")";
    }
}
