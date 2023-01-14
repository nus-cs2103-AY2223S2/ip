import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import utils.DateUtil;

/**
 * Event
 */
class Event extends Task{

    private LocalDateTime from;
    private LocalDateTime to;

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
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
