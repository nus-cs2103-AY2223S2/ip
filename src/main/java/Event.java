package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime to;
    protected LocalDateTime from;
    protected DateTimeFormatter format;

    public Event(String description, String from, String to, DateTimeFormatter format) {
        super(description);
        this.from = LocalDateTime.parse(from, format);
        this.to = LocalDateTime.parse(to, format);
        this.format = format;
    }

    public Event(String description, boolean done, String from, String to, DateTimeFormatter format) {
        super(description, done);
        this.from = LocalDateTime.parse(from, format);
        this.to = LocalDateTime.parse(to, format);
        this.format = format;
    }

    @Override
    public String toString() {
        return "E | " + super.toString() + " | from: " + from.format(format) + " | " + "to: " + to.format(format);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj instanceof Event) {
            Event x = (Event) obj;
            if(this.name.equals(x.name)) {
                return true;
            }
        }
        return false;
    }

}
