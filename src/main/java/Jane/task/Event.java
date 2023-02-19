package jane.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
/**
 * Making an event task
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    /**
     * @param num is Event ID
     * @param description is event description (what to do)
     * @param from is the date that it starts
     * @param to is the date it ends
     */
    public Event(int num, String description, LocalDateTime from, LocalDateTime to) {
        super(num, description);
        this.from = from;
        this.to = to;
    }
    @Override
    public String save() {
        int i = 0;
        if (this.isDone) {
            i = 1;
        }
        return String.format("E|%d| %s |%s|%s", i, this.description, this.from, this.to);
    }
    @Override
    public String toString() {
        String first = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(from);
        String end = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(to);
        return String.format("%d. [E][%s] %s(from %s to %s)", this.num, this.getStatusIcon(), this.description, first, end);
    }

}
