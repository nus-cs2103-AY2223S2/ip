package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Event extends Tasks {
    private LocalDateTime from = null;
    private LocalDateTime to = null;
    public Event(String content, boolean isDone, String from, String to) {
        super(content, isDone);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        this.from = LocalDateTime.parse(from, format);
        this.to = LocalDateTime.parse(to, format);
        this.type = 'E';
    }

    @Override
    public String getDuration() {
        return this.from + " " + this.to;
    }

    @Override
    public String toString() {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
        String from = this.from.format(format);
        String to = this.to.format(format);
        return "[" + this.getTypeIcon() + "]"
                + "[" + this.getStatusIcon() + "] " + this.seeTaskContent() + " (from: " + from + " to: " + to + ")";
    }
}
